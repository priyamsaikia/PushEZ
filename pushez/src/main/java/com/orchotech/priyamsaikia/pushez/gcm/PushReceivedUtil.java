package com.orchotech.priyamsaikia.pushez.gcm;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by Priyam on 25-07-2016.
 */

public class PushReceivedUtil {
    private static final String TAG = PushReceivedUtil.class.getSimpleName();
    static PushEZListener mPushEZListener;

    public static void registerListener(PushEZListener pushEZListener) {
        mPushEZListener = pushEZListener;
    }

    public static void pushReceived(Bundle bundle) {
        Log.d(TAG, "message received");
        mPushEZListener.onPushReceived(bundle);
    }

    public interface PushEZListener {
        void onPushReceived(Bundle bundle);
    }
}
