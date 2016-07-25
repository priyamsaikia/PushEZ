package com.orchotech.priyamsaikia.pushez.gcm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.orchotech.priyamsaikia.pushez.utils.AppConfig;

/**
 * Created by Priyam on 25-07-2016.
 */
public class GCMRegister {
    private static final String TAG = GCMRegister.class.getSimpleName();

    public static void subscribeToGlobalTopic(Context context) {
        Intent intent = new Intent(context, GcmIntentService.class);
        intent.putExtra(GcmIntentService.KEY, GcmIntentService.SUBSCRIBE);
        intent.putExtra(GcmIntentService.TOPIC, AppConfig.TOPIC_GLOBAL);
        context.startService(intent);
    }

    // starting the service to register with GCM
    public static void registerGCM(Context context) {
        Log.d(TAG, "registration code  has been commented");
        Intent intent = new Intent(context, GcmIntentService.class);
        intent.putExtra("key", "register");
        context.startService(intent);
    }

    public static boolean checkPlayServices(Activity activity) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, 9000)
                        .show();
            } else {
                Log.d(TAG, "This device is not supported. Google Play Services not installed!");
                Toast.makeText(activity, "This device is not supported. Google Play Services not installed!", Toast.LENGTH_LONG).show();
            }
            return false;
        }
        return true;
    }

}
