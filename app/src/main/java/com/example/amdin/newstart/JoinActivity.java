package com.example.amdin.newstart;

import android.content.Intent;
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
    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        email = findViewById(R.id.emailEditText);
        pwd = findViewById(R.id.passwordEditText);
        calendar = Calendar.getInstance();
    }

    public void onJoin(View v) {
        String getEdit = email.getText().toString();
        if (getEdit.getBytes().length <= 0) {
            Toast.makeText(this, "You should fill fields", Toast.LENGTH_SHORT).show();
        } else {
            myRef = database.getReference("userinfo");
            String key = myRef.push().getKey();
            myRef.child(key).child("ID").setValue(email.getText().toString());
            myRef.child(key).child("Number").setValue(1);

            myRef = database.getReference("고유번호");

            mAuth.createUserWithEmailAndPassword(email.getText().toString(), pwd.getText().toString()).addOnCompleteListener(
                    this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "SignUp Failed", Toast.LENGTH_SHORT).show();
                            } else {
                                String key = myRef.push().getKey();
                                myRef.child(key).child("ID").setValue(email.getText().toString());
                                myRef.child(key).child("Password").setValue(pwd.getText().toString());
                                myRef.child(key).child("Number").setValue("고유번호");
                                myRef.child(key).child("year").setValue(calendar.get(Calendar.YEAR));
                                myRef.child(key).child("month").setValue(calendar.get(Calendar.MONTH));
                                myRef.child(key).child("day").setValue(calendar.get(Calendar.DAY_OF_MONTH) + 1);
                                myRef.child(key).child("diary").child("").setValue("");
                                Toast.makeText(getApplicationContext(), "SignUp Success", Toast.LENGTH_SHORT).show();
                                Intent back = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(back);
                                //회원 가입시 DB정보로 사용자 정보와 날짜 정보를 항목으로저장하고
                                //성공적으로 회원가입이 되었다면 로그인 페이지로 바로 간다.

                            }
                        }
                    }

            );
        }
    }

    public void onCancel(View v) {
        finish();
    }
}
