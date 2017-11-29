package com.example.amdin.newstart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by amdin on 2017-11-29.
 */

public class DiaryListActivity extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        ArrayList<Item> item;
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        item =Item.createContactsList(20);
        ItemAdapter adapter=new ItemAdapter(this,item);
        recyclerView.setAdapter(adapter);



    }
}
