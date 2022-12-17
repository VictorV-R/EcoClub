package com.example.ecoclub.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.cognito.result.AWSCognitoAuthSignOutResult;
import com.amplifyframework.auth.cognito.result.GlobalSignOutError;
import com.amplifyframework.auth.cognito.result.HostedUIError;
import com.amplifyframework.auth.cognito.result.RevokeTokenError;
import com.amplifyframework.core.Amplify;
import com.example.ecoclub.AuthenticationActivity;
import com.example.ecoclub.MainActivity;
import com.example.ecoclub.R;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    private View view;
    private RadioGroup radioGroupButton;
    private AppCompatRadioButton rbLeft, rbRight;
    private Button btn_logout, btn_remove;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        rbLeft = view.findViewById(R.id.radioButtonLeft);
        rbRight = view.findViewById(R.id.radioButtonRight);

        rbLeft.setOnCheckedChangeListener(new Radio_check());
        rbRight.setOnCheckedChangeListener(new Radio_check());

        btn_logout = view.findViewById(R.id.btn_logout);
        btn_remove = view.findViewById(R.id.btn_remove);


        loadProfile();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLogout();
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        return view;
    }

    class Radio_check implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(rbLeft.isChecked()) {
                rbLeft.setTextColor(Color.BLACK);
                rbRight.setTextColor(Color.GREEN);
            }
            else if(rbRight.isChecked()) {
                rbRight.setTextColor(Color.BLACK);
                rbLeft.setTextColor(Color.GREEN);
            }
        }
    }

    public void loadProfile(){
        Amplify.Auth.fetchUserAttributes(
                result -> {
                    Log.i("AuthQuickStart", "IdentityId: " + result.get(2).getValue());
                },
                error -> Log.e("AuthQuickStart", error.toString())
            );
    }
    /*[AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=sub}, value=ac1297fe-4d74-4c66-b3c1-42b43835f1a6},
    {key=AuthUserAttributeKey {attributeKey=email_verified}, value=true},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=name}, value=Walter Huaracha},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=phone_number_verified},value=false},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=phone_number},value=+51915909135},
    AuthUserAttribute {key=AuthUserAttributeKey {attributeKey=email}, value=whuaracha@unsa.edu.pe}]*/

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
