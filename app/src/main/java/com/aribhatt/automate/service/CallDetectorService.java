package com.aribhatt.automate.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.aribhatt.automate.R;
import com.aribhatt.automate.data.model.MissedCall;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CallDetectorService extends Service {
    TelephonyManager tm;
    CallStateListener callStateListener;
    static List<String> numbers = new ArrayList<>();
    private String CHANNEL_ID = "0X000";
    public CallDetectorService() {
    }

    @Override
    public void onCreate() {
        try{
            callStateListener = new CallStateListener();
            tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
            if(tm != null) {
                tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
            }
            super.onCreate();
        }catch (Exception e){

        }

        Log.d("CALL DETECTOR SERVICE", "Creating service....");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("CALL DETECTOR SERVICE", "Starting service....");
        //return super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("CALL DETECTOR SERVICE", "Destroying service....");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Observable<String> getObservable(){
        return Observable.fromIterable(numbers).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.common_google_signin_btn_text_dark_normal)
                .setContentTitle("My notification")
                .setContentText("Detail text")
                .setPriority(NotificationCompat.PRIORITY_HIGH);


    }

    private class CallStateListener extends PhoneStateListener{

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            boolean RANG = false;
            boolean RECEIVED = false;
            boolean MISSED = false;
            super.onCallStateChanged(state, incomingNumber);
            switch (state){
                case TelephonyManager.CALL_STATE_RINGING:
                    RANG = true;
                    Log.d("RINGING CALL", incomingNumber);

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    RECEIVED = true;
                    Log.d("RECEIVED CALL", incomingNumber);

                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    if(RANG && !RECEIVED){
                        MISSED = true;
                        numbers.add(incomingNumber);
                        Log.d("MISSED CALL", incomingNumber);
                    }
                    break;

            }
        }
    }
}
