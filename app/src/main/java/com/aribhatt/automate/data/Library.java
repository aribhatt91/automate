package com.aribhatt.automate.data;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.provider.ContactsContract;

import com.aribhatt.automate.data.model.Contact;
import com.aribhatt.automate.data.model.MissedCall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aribhatt on 29/12/17.
 */

public class Library {
    public static final int PERMISSION_REQUEST_ID = 0x01;

    public static final String[] contactsProjection ={
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.Contacts.CUSTOM_RINGTONE,
            ContactsContract.Contacts.STARRED,
            ContactsContract.Contacts.LAST_TIME_CONTACTED

    };


    //
    //          PERMISSION METHODS
    //

    @TargetApi(23)
    public static boolean hasPermission(Context context){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.READ_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_CONTACTS)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_GRANTED
                        && context.checkSelfPermission(Manifest.permission.WRITE_SETTINGS)
                        == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity) {
        activity.requestPermissions(
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.PROCESS_OUTGOING_CALLS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_SETTINGS

                },
                PERMISSION_REQUEST_ID);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean previouslyRequestedPermission(Activity activity) {
        return
                activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WAKE_LOCK)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_PHONE_STATE)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.PROCESS_OUTGOING_CALLS)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.RECEIVE_BOOT_COMPLETED)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_CONTACTS)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_CONTACTS)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.RECORD_AUDIO)
                        || activity.shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_SETTINGS);

    }



    public static void checkAndSetPermissions(Context context){
        if(!hasPermission(context)){
            if(!previouslyRequestedPermission((Activity)context)){
                requestPermission((Activity) context);
            }
        }
    }

    public static ArrayList<Contact> scanContacts(Context context){
        ArrayList<Contact> contacts = new ArrayList<>();
        String order = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

        Cursor c = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                contactsProjection,
                null,
                null,
                order);

        if(c!=null && c.moveToFirst()){
            for(int i=0; i<c.getCount(); i++){
                c.moveToPosition(i);
                ArrayList<String> phones = getPhoneNumbers(context, c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                contacts.add(new Contact(
                        c.getLong(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID)),
                        c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)),
                        c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)),
                        c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.CUSTOM_RINGTONE)),
                        phones
                ));
            }
            c.close();
        }


        return contacts;
    }

    public static ArrayList<String> getPhoneNumbers(Context context, long contactId){
        ArrayList<String> numbers = new ArrayList<>();

        try{
            Cursor phones = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
            while (phones!=null && phones.moveToNext()) {
                String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                switch (type) {
                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                        // do something with the Home number here...
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                        // do something with the Mobile number here...
                        break;
                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                        // do something with the Work number here...
                        break;
                }

                numbers.add(number);

            }
            phones.close();
        }catch (Exception e){

        }

        return numbers;
    }

    public static ArrayList<MissedCall> getMissedCallsFromLog(Context context, int limit){
        ArrayList<MissedCall> calls = new ArrayList<>();
        try{
            String[] projection = { CallLog.Calls.CACHED_NAME, CallLog.Calls.DATE, CallLog.Calls.NEW, CallLog.Calls.IS_READ, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE };
            String where = CallLog.Calls.TYPE+"="+CallLog.Calls.MISSED_TYPE;
            Cursor c = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, where, null, null);
            if(c == null){
                return calls;
            }
            c.moveToFirst();
            c.close();
        }catch (SecurityException e){

        }catch (NullPointerException e){

        }
        return calls;
    }
}
