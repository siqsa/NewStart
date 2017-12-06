package com.example.amdin.newstart;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.write_btn,menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent=new Intent(getApplicationContext(),WriteActivity.class);
                startActivity(intent);
                return true;
                default:return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed(){
        mAuth.signOut();
        Toast.makeText(getApplicationContext(),"Sign out",Toast.LENGTH_SHORT).show();
       Intent a =new Intent(getApplicationContext(),LoginActivity.class);
       startActivity(a);
    }
}
