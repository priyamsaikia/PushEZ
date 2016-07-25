package com.orchotech.priyamsaikia.pushez.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.orchotech.priyamsaikia.pushez.gcm.GCMRegister;

/**
 * Created by Priyam on 25-07-2016.
 */
public class PushEZ {
    private static final String TAG = PushEZ.class.getSimpleName();
    static BroadcastReceiver mLocalRegistrationBroadcastReceiver;
    private static RegistrationListener mRegistrationListener;

    public static void registerPushEZTokenReceiver(Context context) {
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(context).registerReceiver(mLocalRegistrationBroadcastReceiver,
                new IntentFilter(AppConfig.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(context).registerReceiver(mLocalRegistrationBroadcastReceiver,
                new IntentFilter(AppConfig.PUSH_NOTIFICATION));
    }

    public static void unregisterPushEZTokenReceiver(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(mLocalRegistrationBroadcastReceiver);
    }

    public static void getRegistrationId(Activity activity) {
        mLocalRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(AppConfig.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    String token = intent.getStringExtra("token");
                    Log.i(TAG, "GCM registration token: " + token);
                    GCMRegister.subscribeToGlobalTopic(context);

                } else if (intent.getAction().equals(AppConfig.SENT_TOKEN_TO_SERVER)) {
                    // gcm registration id is stored in our server's MySQL

                    Log.i(TAG, "GCM registration token is stored in server!");

                } else if (intent.getAction().equals(AppConfig.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    Log.i(TAG, "Push notification is received!");
                }
            }
        };

        if (TextUtils.isEmpty(SharedPref.getRegId(activity))) {
            if (GCMRegister.checkPlayServices(activity)) {
                GCMRegister.registerGCM(activity);
            }
        } else {
            Log.i(TAG, "already registered with regId =" + SharedPref.getRegId(activity));
        }
    }

    public static void registerRegistrationListener(RegistrationListener registrationListener) {
        mRegistrationListener = registrationListener;
    }

    public static void registrationComplete(String registrationId) {
        mRegistrationListener.onRegistrationComplete(registrationId);
    }


    public static void initPushEZ(Context context, String gcmSenderId) {
        SharedPref.writeSenderId(context, gcmSenderId);
    }

    public interface RegistrationListener {
        void onRegistrationComplete(String registrationId);
    }

}
