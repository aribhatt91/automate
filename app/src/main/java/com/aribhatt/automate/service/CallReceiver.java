package com.aribhatt.automate.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.util.Calendar;

/**
 * Created by aribhatt on 28/12/17.
 */

public class CallReceiver extends BroadcastReceiver {
    private static boolean ring=false;
    private static boolean callReceived=false;
    private String callerPhoneNumber;
    private Context saveContext;

    @Override
    public void onReceive(Context context, Intent intent) {


    }
}
