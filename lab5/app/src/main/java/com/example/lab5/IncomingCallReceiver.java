package com.example.lab5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class IncomingCallReceiver extends BroadcastReceiver{
    //Context context;

    @Override
    public void onReceive(Context context, Intent intent){
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status.isEmpty()){
            status = "No Internet Connection";
        }
        if(intent.getAction() == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context,"power connected",Toast.LENGTH_SHORT).show();
        } else if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED){
            Toast.makeText(context,"power disconnected",Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        } else if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){

            Toast.makeText(context, "making call", Toast.LENGTH_LONG).show();
        }
        /*TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
        if (tm.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {

            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);


        }*/

        //Toast.makeText(context,"Broadcast Received",Toast.LENGTH_SHORT).show();
        try{
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context, "Phone Is Ringing", Toast.LENGTH_LONG).show();
            }

            if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context, "Call Recieved", Toast.LENGTH_LONG).show();
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context, "Phone Is Idle", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){e.printStackTrace();}
    }


}