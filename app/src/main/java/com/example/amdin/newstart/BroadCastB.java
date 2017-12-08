package com.example.amdin.newstart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by amdin on 2017-12-06.
 */

public class BroadCastB extends Service {
    public IBinder onBind(Intent intent){
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationManager notify_manager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //set up an intent that goes to the main activity
        Intent intent_main_activity = new Intent(this.getApplicationContext(),DiaryListActivity.class);
        //make the notification parameters
        PendingIntent pending_intent_main_activity=PendingIntent.getActivity(this,0,intent_main_activity,0);
        Notification notification_popup=new Notification.Builder(this).setContentTitle("어제의 일기로")
                .setContentText("피드백을 해보세요!")
                .setContentIntent(pending_intent_main_activity)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setAutoCancel(true)
                .build();
        //set up the notification call command
        notify_manager.notify(0,notification_popup);


       return START_NOT_STICKY;
    }
}
