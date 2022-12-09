package com.example.ecoclub.comunity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ecoclub.MainActivity;
import com.example.ecoclub.fragments.ComunityDescriptionFragment;

public class ComunityBroadcastReceiver extends BroadcastReceiver {

    private static Intent intentActivityMain = new Intent();

    @Override
    public void onReceive(Context context, Intent intent) {
        intentActivityMain.setClass(context.getApplicationContext(), MainActivity.class);

        String mensaje = "-1";

        //Acciones Broadcast Receiver===============================================
        if(intent.getAction().equals("SOME_ACTION_DESCRIPTION_COMUNITY") ||
                intent.getAction().equals("SOME_ACTION_DESCRIPTION_MY_COMUNITY")) {

            //DESTINO: Description Comunity
            mensaje = intent.getStringExtra(ComunityDescriptionFragment.DESTINY);

            intentActivityMain.putExtra("BroadcastReceiverDescriptionComunity", mensaje);
            context.startActivity(intentActivityMain);
        }
        //===============================================================================

    }
}
