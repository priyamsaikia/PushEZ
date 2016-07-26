<h1>PUSHEZ - Push Easy</h1>
PushEZ is a library which makes it really easy to implement Google Cloud Messaging (Push Notification Messaging). All you have to do is follow the following three steps! This library completely handles the device registration part and push message receiving part. Thereby, making it extremely easy for developers to use GCM.
You can chose to handle the messages as you want (eg, sending notification, or saving it in database, etc)

##Installation 
Add the following to your app level build.gradle
```
dependencies {
		.
		.
	    compile 'com.orchotech.priyamsaikia.lib-pushez:pushez:beta.1.0.3'
	}
```



<h1>GET STARTED : Get Your Sender ID and (note the Server API key) in the link below</h1>

https://developers.google.com/cloud-messaging/android/start

<h2>1. Initialise PushEZ with Google Sender ID, Register Device to get Google Cloud Device Registration ID</h2>
<h5> MyActivity.java </h5>

```java
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushEZ.initPushEZ(this, "your_sender_id");
        PushEZ.getRegistrationId(this);
        PushEZ.registerRegistrationListener(this);
        
    }


    @Override
    protected void onResume() {
        super.onResume();
        PushEZ.registerPushEZTokenReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PushEZ.unregisterPushEZTokenReceiver(this);
    }
```

<h2>2. Save the Google Cloud Device Registration ID</h2>
<h5>MyActivity.java</h5>
<h3>2. a) implement PushEZ.RegistrationListener in the activity </h3>
<h3>2. b) implement method onRegistrationComplete(String registrationId)</h3>


```java
    @Override
    public void onRegistrationComplete(String registrationId) {
        Log.d("take your token", registrationId);
    }
```
<h2>3. Receive Push Notifications </h2>
<h3>3 a) In the BroadCastReceiver, implement PushReceivedUtil.PushEZListener</h3>
<h3>3 b) Implement method</h3>

```java
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
    }
}
```

<h2>4. Register the receiver in the AndroidManifest.xml</h2>
<h5>AndroidManifest.xml</h5>

```xml
<receiver android:name=".MyReceiver">
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
</receiver>
```
To check if the app is working well, 

a)  Note the token ID or registration ID from console, that you receive in the method 

```java
public void onRegistrationComplete(String registrationId) {
        Log.d("take your token", registrationId);
    }
```

b)  Note the Server API Key from Google Developers' Console     
c)  Visit <h5>http://androidbegin.com/tutorial/gcm.html </h5>enter your key and registration id in the provided form, enter message
  and check your console to know if you have received the message (or generate a notification in this method 

```java
@Override
    public void onPushReceived(Bundle bundle) {
        Log.d("my receiver", bundle.getString("message"));
    }
```

Thanks !
    
<h2>LICENSE</h2>

    Copyright 2016 Priyam Saikia

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



