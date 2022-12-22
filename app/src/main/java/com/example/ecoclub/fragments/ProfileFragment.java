package com.example.ecoclub.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.core.Amplify;
import com.example.ecoclub.AuthenticationActivity;
import com.example.ecoclub.R;


public class ProfileFragment extends Fragment {

    private View view;
    //private RadioGroup radioGroupButton;
    private Fragment profileDatosFragment;
    private Fragment profileLogrosFragment;
    FragmentTransaction transaction;
    private AppCompatRadioButton rbLeft, rbRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        rbLeft = view.findViewById(R.id.radioButtonLeft);
        rbRight = view.findViewById(R.id.radioButtonRight);
        rbLeft.setOnCheckedChangeListener(new Radio_check());
        rbRight.setOnCheckedChangeListener(new Radio_check());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileDatosFragment = new ProfileDatosFragment();
        transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, profileDatosFragment).addToBackStack(null).commit();
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //limpiando back stack
            clearBackStack();
            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GRAY);
                profileDatosFragment = new ProfileDatosFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, profileDatosFragment).addToBackStack(null).commit();
            }
            else if(rbRight.isChecked()) {
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GRAY);
                profileLogrosFragment = new ProfileLogrosFragment();
                transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.child_fragment_container, profileLogrosFragment).addToBackStack(null).commit();
            }
        }
    }
    //para limpiar el back stack*************************************************
    private void clearBackStack() {
        FragmentManager manager = getChildFragmentManager();
        //solo dejamos el Home Fragment(index 1)
        if (manager.getBackStackEntryCount() > 1) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(1);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

    public void loadLogout(){
        Amplify.Auth.signOut( signOutResult -> {
            if (signOutResult instanceof AWSCognitoAuthSignOutResult.CompleteSignOut) {
                // Sign Out completed fully and without errors.
                Log.i("AuthQuickStart", "Signed out successfully");
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.PartialSignOut) {
                // Sign Out completed with some errors. User is signed out of the device.
                AWSCognitoAuthSignOutResult.PartialSignOut partialSignOutResult =
                        (AWSCognitoAuthSignOutResult.PartialSignOut) signOutResult;

                HostedUIError hostedUIError = partialSignOutResult.getHostedUIError();
                if (hostedUIError != null) {
                    Log.e("AuthQuickStart", "HostedUI Error", hostedUIError.getException());
                    // Optional: Re-launch hostedUIError.getUrl() in a Custom tab to clear Cognito web session.
                }

                GlobalSignOutError globalSignOutError = partialSignOutResult.getGlobalSignOutError();
                if (globalSignOutError != null) {
                    Log.e("AuthQuickStart", "GlobalSignOut Error", globalSignOutError.getException());
                    // Optional: Use escape hatch to retry revocation of globalSignOutError.getAccessToken().
                }

                RevokeTokenError revokeTokenError = partialSignOutResult.getRevokeTokenError();
                if (revokeTokenError != null) {
                    Log.e("AuthQuickStart", "RevokeToken Error", revokeTokenError.getException());
                    // Optional: Use escape hatch to retry revocation of revokeTokenError.getRefreshToken().
                }
            } else if (signOutResult instanceof AWSCognitoAuthSignOutResult.FailedSignOut) {
                AWSCognitoAuthSignOutResult.FailedSignOut failedSignOutResult =
                        (AWSCognitoAuthSignOutResult.FailedSignOut) signOutResult;
                // Sign Out failed with an exception, leaving the user signed in.
                Log.e("AuthQuickStart", "Sign out Failed", failedSignOutResult.getException());
            }
        });

        Intent intent = new Intent(getContext(), AuthenticationActivity.class);
        startActivity(intent);
    }

    public void delete(){
        Amplify.Auth.deleteUser(
                () -> Log.i("AuthQuickStart", "Delete user succeeded"),
                error -> Log.e("AuthQuickStart", "Delete user failed with error " + error.toString())
        );
    }
}
