package com.example.amdin.newstart;

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

       return START_NOT_STICKY;
    }
}
