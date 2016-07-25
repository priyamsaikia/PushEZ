In your activity, put these lines of code:
To start Google Cloud Device Registration Process:

//start activity code

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pushez start code
        PushEZ.getRegistrationId(this);
        PushEZ.registerRegistrationListener(this);
        //pushez end code

    }


    @Override
    protected void onResume() {
        super.onResume();
        //pushez start code
        PushEZ.registerPushEZTokenReceiver(this);
        //pushez end code
    }

    @Override
    protected void onPause() {
        super.onPause();
        //pushez start code
        PushEZ.unregisterPushEZTokenReceiver(this);
        //pushez end code
    }

Now, to get the registration id, once the registration process is complete,
implement PushEZ.RegistrationListener in the activity and implement method


    @Override
    public void onRegistrationComplete(String registrationId) {
        Log.d("take your token", registrationId);
    }



    //end of Activity code


   Now, to receive push messages, add this code:

   //start receiver code
public class MyReceiver extends BroadcastReceiver implements PushReceivedUtil.PushEZListener {

    private PushReceivedUtil.PushEZListener  mPushEZListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        mPushEZListener = this;
        PushReceivedUtil.registerListener(mPushEZListener);
    }

    @Override
    public void onPushReceived(Bundle bundle) {
        Log.d("my receiver", bundle.getString("message"));
        //this is where you need to handle the message, eg. send a notification, ringtone, vibration, etc.
    }
}//end receiver code

Register the receiver in the AndroidManifest.xml inside application tag
//start of AndroidManifest.xml code
<receiver android:name=".MyReceiver">
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
</receiver>

//end of AndroidManifest.xml code
