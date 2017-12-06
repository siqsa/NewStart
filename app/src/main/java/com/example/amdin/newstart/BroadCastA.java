package com.example.amdin.newstart;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by amdin on 2017-12-06.
 */

    public class BroadCastA extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Intent service_intent=new Intent(context,BroadCastB.class);
        context.startService(service_intent);

    }
}
