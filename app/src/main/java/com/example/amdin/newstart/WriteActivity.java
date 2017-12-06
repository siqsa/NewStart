package com.example.amdin.newstart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tidy1 on 2017-11-28.
 */

public class WriteActivity extends AppCompatActivity {

    private TextView datetextView;

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
