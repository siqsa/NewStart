package com.example.amdin.newstart;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tidy1 on 2017-11-28.
 */

public class WriteActivity extends AppCompatActivity {

    private TextView datetextView;
    private TextView alarm;
    AlarmManager alarm_manager;
    int hour,minute;
   /* private TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int  Minute) {
            hour=hourOfDay;
            minute=Minute;
            Intent intent=new Intent(getApplicationContext(),BroadcastD.class);
            alarm_manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minute);
            calendar.set(Calendar.SECOND,0);
            intent.putExtra("extra","Time");
            pending_intent = PendingIntent.getBroadcast(interfaceActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            if(successRate==100){
                alarm_manager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pending_intent);
            }
            else{
                alarm_manager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pending_intent);
                alarm_manager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),(1000*60),pending_intent);
            }
            yes.setText(hour+"시"+minute+"분");
            Toast.makeText(getApplicationContext(),hour + "시" + minute + "분에 알림이 시작됩니다.", Toast.LENGTH_SHORT).show();
        }
    };
*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        datetextView = (TextView)findViewById(R.id.dateTextView);
        setDate();
    }

    public void setDate() {
        Date now = new Date();
        DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, Locale.KOREA);
        String getTime = format.format(now);
        datetextView.setText(getTime);
    }

    public void submitDiary(View v) {

    }
}
