package com.example.amdin.newstart;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
/**
 * Created by tidy1 on 2017-11-28.
 */
public class WriteActivity extends AppCompatActivity {
    public TextView datetextView;
    public EditText diaryEditText;
    public TextView alarm;
    AlarmManager alarm_manager;
    int hour,minute;
    Calendar calendar;
    int year, month, day;
    private FirebaseAuth mAuth;
    public FirebaseDatabase database;
    private PendingIntent pending_intent;
    private DatabaseReference myRef;
    private SharedPreferences sp;


   private TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int  Minute) {
            hour=hourOfDay;
            minute=Minute;
            Intent intent=new Intent(getApplicationContext(),BroadCastA.class);
            alarm_manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                       Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minute);
            calendar.set(Calendar.SECOND,0);
            pending_intent = PendingIntent.getBroadcast(WriteActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarm_manager.setExact(AlarmManager.RTC,calendar.getTimeInMillis(),pending_intent);
               // alarm_manager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),(1000*60),pending_intent);
                                 Toast.makeText(getApplicationContext(),hour + "시" + minute + "분에 알림이 시작됩니다.", Toast.LENGTH_SHORT).show();
                                 alarm.setText(hour+"시"+minute+"분");
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        datetextView = (TextView)findViewById(R.id.dateTextView);
        diaryEditText = (EditText)findViewById(R.id.writeEditText);
        alarm=(TextView)findViewById(R.id.alarmText);
        database = FirebaseDatabase.getInstance();
        sp = getSharedPreferences("myFile", 0);
        Long value = sp.getLong("serial_number", 0);
        myRef = database.getReference("serial_number").child(value.toString());
        alarm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TimePickerDialog dialog = new TimePickerDialog(WriteActivity.this, TimePickerDialog.THEME_HOLO_LIGHT,listener, 00, 00, false);
                dialog.show();
            }
        });
        setDate();
    }
    public void setDate() {
        calendar=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String date;
        date = year + "년 " + month + "월 " + day + "일";
        datetextView.setText(date);
    }
    public void submitDiary(View v) {
        String key = myRef.push().getKey();
        myRef.child(key).child("Year").setValue(String.valueOf(year));
        myRef.child(key).child("Month").setValue(String.valueOf(month));
        myRef.child(key).child("Day").setValue(String.valueOf(day));
        myRef.child(key).child("Diary").setValue(diaryEditText.getText().toString());
        String context=diaryEditText.getText().toString();
        SharedPreferences.Editor contextEditor = sp.edit();
       contextEditor.putString("text", context);
        contextEditor.commit();
        finish();
    }
}
