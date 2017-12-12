package com.example.amdin.newstart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by amdin on 2017-11-29.
 */

public class DiaryListActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private SharedPreferences sp;
    private Long number;
    private ArrayList<Item> item = new ArrayList<>();
    private ItemAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter  = new ItemAdapter(this, item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        sp = getSharedPreferences("myFile", 00);
        number = sp.getLong("serial_number", 0);
        myRef = database.getReference("serial_number").child(number.toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item.clear();
                adapter.notifyDataSetChanged();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String year = snapshot.child("Year").getValue(String.class);
                    String month = snapshot.child("Month").getValue(String.class);
                    String day = snapshot.child("Day").getValue(String.class);
                    String diary = snapshot.child("Diary").getValue(String.class);

                    item.add(new Item(year, month, day, diary));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }




    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.write_btn, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        mAuth.signOut();
        Toast.makeText(getApplicationContext(), "Sign out", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(a);
        finish();
    }
}
