package com.example.amdin.newstart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by tidy1 on 2017-11-28.
 */

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public FirebaseDatabase database;
    DatabaseReference myRef;
private EditText email;
private EditText pwd;
    String userId;
    Date date;
   Calendar calendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("user");
    email=findViewById(R.id.email);
    pwd=findViewById(R.id.password);
    userId="ID";
    calendar=Calendar.getInstance();
    }
    public void onJoin(View v){
String getEdit=email.getText().toString();
if(getEdit.getBytes().length<=0){
    Toast.makeText(this,"You should fill fields",Toast.LENGTH_SHORT).show();
}

       else{ mAuth.createUserWithEmailAndPassword(email.getText().toString(),pwd.getText().toString()).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"SignUp Failed",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            String key=myRef.push().getKey();
                            myRef.child(key).child("ID").setValue(email.getText().toString());
                            myRef.child(key).child("Password").setValue(pwd.getText().toString());
                            myRef.child(key).child("userKey").setValue(key);
                            myRef.child(key).child("year").setValue(Calendar.YEAR);
                            myRef.child(key).child("month").setValue(Calendar.MONTH);
                            myRef.child(key).child("day").setValue(Calendar.YEAR);
                            myRef.child(key).child("text").setValue("일기내용");
                            Toast.makeText(getApplicationContext(),"SignUp Success",Toast.LENGTH_SHORT).show();


                        }
                    }
                }

        );}
    }
    public void onCancel(View v){
        finish();
    }
}
