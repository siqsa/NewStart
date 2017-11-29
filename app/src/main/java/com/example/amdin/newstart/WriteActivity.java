package com.example.amdin.newstart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tidy1 on 2017-11-28.
 */

public class WriteActivity extends AppCompatActivity {

    private TextView datetextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        setTitle("Write");

        datetextView = (TextView)findViewById(R.id.dateTextView);

    }

    public void setDate() {

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 e요일");
        String getTime = sdf.format(date);

        datetextView.setText(getTime);
    }

    public void submitDiary(View v) {

    }
}
