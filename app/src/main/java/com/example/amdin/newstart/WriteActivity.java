package com.example.amdin.newstart;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

/**
 * Created by tidy1 on 2017-11-28.
 */

public class WriteActivity extends AppCompatActivity {

    private TextView datetextView;
    private EditText diaryEditText;
    private TextView alarm;
    AlarmManager alarm_manager;
    int hour,minute;
    Calendar calendar;
    int year, month, day;
    private FirebaseAuth mAuth;
    public FirebaseDatabase database;
    DatabaseReference myRef;

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
            }/
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
        diaryEditText = (EditText)findViewById(R.id.writeEditText);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("고유번호");

        setDate();
    }

    public void setDate() {
        calendar=Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        String date;
        date = year + "년 " + month + "월 " + day + "일";
        datetextView.setText(date);

    }

    public void submitDiary(View v) {
        String key = myRef.push().getKey();
        myRef.child(key).child("Year").setValue(year);
        myRef.child(key).child("Month").setValue(month);
        myRef.child(key).child("Day").setValue(day);
        myRef.child(key).child("Diary").setValue(diaryEditText.getText());
    }
}
