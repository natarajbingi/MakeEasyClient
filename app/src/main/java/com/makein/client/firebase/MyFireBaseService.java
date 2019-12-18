package com.makein.client.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;
import com.makein.client.fragment.HomeFragment;

public class MyFireBaseService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onNewToken(String s) {

        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        Sessions.setUserObject(getApplicationContext(),token, Controller.token);

    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        if(remoteMessage.getNotification() != null) {
            Log.d(TAG, "From: " + remoteMessage.getFrom());
            Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
            notifyUser(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());
        }

    }

    public void notifyUser(String from, String notification){
        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(from,notification,new Intent(getApplicationContext(), HomeFragment.class));
    }


}
