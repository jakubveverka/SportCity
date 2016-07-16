package com.hackprague.sportcity.services;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hackprague.sportcity.utilities.Logger;

/**
 * Created by jakubveverka on 16.07.16.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        Logger.d(TAG, "From: " + remoteMessage.getFrom());
        Logger.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
    }

}
