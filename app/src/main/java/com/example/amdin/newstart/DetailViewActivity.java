package com.example.amdin.newstart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailViewActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView dateTextView;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private SharedPreferences sp;
    private Item item;
    private String key = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Intent intent = getIntent();
        item = (Item) intent.getSerializableExtra("item");
        database = FirebaseDatabase.getInstance();
        sp = getSharedPreferences("myFile", 00);
        Long value = sp.getLong("serial_number", 0);
        myRef = database.getReference("serial_number").child(value.toString());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String year = snapshot.child("Year").getValue(String.class);
                    String month = snapshot.child("Month").getValue(String.class);
                    String day = snapshot.child("Day").getValue(String.class);
                    String diary = snapshot.child("Diary").getValue(String.class);

                    if (item.getYear().equals(year) && item.getMonth().equals(month) && item.getDay().equals(day) && item.getText().equals(diary)) {
                        key = snapshot.getKey();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        String date = item.getYear() + "년 " + item.getMonth() + "월 " + item.getDay() + "일";
        String text = item.getText();
        dateTextView = findViewById(R.id.DetaildateTextView);
        dateTextView.setText(date);

        editText = findViewById(R.id.DetailEditText);
        editText.setText(text);

        button = findViewById(R.id.DetaildViewButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(key).child("Diary").setValue(editText.getText().toString());
                editText.setEnabled(false);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
