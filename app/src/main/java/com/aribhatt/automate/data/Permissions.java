package com.aribhatt.automate.data;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by aribhatt on 21/02/18.
 */

public class Permissions {
    public static final int PERMISSION_REQUEST_ID = 0x01;


    @TargetApi(23)
    public static boolean hasContactsPermission(Context context){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_SETTINGS)
                        == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestContactsPermission(Activity activity) {
        activity.requestPermissions(
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.WRITE_SETTINGS

                },
                PERMISSION_REQUEST_ID);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean previouslyRequestedContactsPermission(Activity activity) {
        return
                activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_CONTACTS)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_CONTACTS)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_SETTINGS);

    }



    public static void checkContactsPermission(Context context){
        if(!hasContactsPermission(context)){
            if(!previouslyRequestedContactsPermission((Activity)context)){
                requestContactsPermission((Activity) context);
            }
        }
    }
}
