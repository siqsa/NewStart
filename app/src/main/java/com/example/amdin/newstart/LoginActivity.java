package com.example.amdin.newstart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private SharedPreferences sp;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("myFile", Activity.MODE_PRIVATE);

    }

    public void onButtonLogin(View v) {
        email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        if (email.getBytes().length <= 0) {
            Toast.makeText(this, "사용자 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        } else {
                            // 고유번호 가져오기.
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("user");

                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                        String id = snapshot.child("ID").getValue(String.class);
                                        if (id.equals(email)) {
                                            Long value = snapshot.child("Number").getValue(Long.class);

                                            Toast.makeText(LoginActivity.this, "데이터 로딩 성공", Toast.LENGTH_SHORT).show();

                                            SharedPreferences.Editor editor = sp.edit();
                                            editor.putLong("serial_number", value);
                                            editor.commit();

                                            break;

                                        }


                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError error) {
                                    Toast.makeText(LoginActivity.this, "데이터 로딩 에러", Toast.LENGTH_SHORT).show();
                                }
                            });

                            Intent intent = new Intent(LoginActivity.this, DiaryListActivity.class);
                            startActivity(intent);

                        }
                    }
                });
    }

        public void onClickSingIn(View v) {

        Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
        startActivity(intent);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public void onBackPressed() {
        super.onBackPressed();

    }
}
