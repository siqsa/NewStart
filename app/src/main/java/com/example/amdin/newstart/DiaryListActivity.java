package com.example.amdin.newstart;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by amdin on 2017-11-29.
 */

public class DiaryListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        ArrayList<Item> item;
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        item =Item.createContactsList(20);
        ItemAdapter adapter=new ItemAdapter(this,item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void onBackPressed(){
        mAuth.signOut();

        Toast.makeText(getApplicationContext(),"Sign out",Toast.LENGTH_SHORT).show();
        finish();
    }
}
