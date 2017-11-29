package com.example.amdin.newstart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tidy1 on 2017-11-28.
 */

public class JoinActivity extends AppCompatActivity {

EditText email;
EditText pwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

    email=(EditText)findViewById(R.id.email);
    pwd=(EditText)findViewById(R.id.password);
    }
    public void onJoin(View v){

    }
    public void onCancel(View v){
        finish();
    }
}
