package com.aribhatt.automate.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//import com.google.android.gms.location.LocationRequest;

public class LocationChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        try {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                // Trigger the alarm function that detects current location every 5 mins
            }
        }catch (Exception e){

        }

    }

//    protected void createLocationRequest() {
//        LocationRequest mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(10000);
//        mLocationRequest.setFastestInterval(5000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//    }
}
