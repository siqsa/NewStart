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


/**
 * Created by tidy1 on 2017-11-28.
 */

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
EditText email;
EditText pwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
    email=findViewById(R.id.email);
    pwd=findViewById(R.id.password);
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
