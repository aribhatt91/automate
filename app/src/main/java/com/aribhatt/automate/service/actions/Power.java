package com.aribhatt.automate.service.actions;

import android.content.Context;
import android.provider.Settings;

public class Power {
    public static void setBrightness(int brightness){

    }

    public static void setManualMode(Context context){
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);  //this will set the manual mode (set the automatic mode off)
    }

    public static void setAutoMode(Context context){
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);  //this will set the manual mode (set the automatic mode off)
    }
}
