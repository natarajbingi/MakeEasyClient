package com.makein.client.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

import com.makein.client.R;

public class MyNotificationManager {

    private Context context;
    public static final int NOTIFICATION_ID = 1234;

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    public void showNotification(String from, String notification, Intent intent){
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notificn = builder.setSmallIcon(R.drawable.logo_easymake)
                                        .setAutoCancel(true)
                                        .setContentIntent(pendingIntent)
                                        .setContentTitle(from)
                                        .setContentText(notification)
                                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.logo_easymake)).build();

        notificn.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notificn);
    }

}
