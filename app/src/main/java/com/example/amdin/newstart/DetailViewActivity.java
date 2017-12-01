package com.example.amdin.newstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    private InputMethodManager imm; //전역변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        String text = "안\n녕\n하\n세\n요";

        editText = findViewById(R.id.DetailEditText);
        editText.setText(text);
        editText.setFocusable(false);
        editText.setClickable(false);

        button = findViewById(R.id.DetaildViewButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = button.getText().toString();

                if (currentText.equals("수정하기")) {
                    editText.setFocusableInTouchMode(true);
                    editText.setClickable(true);
                    editText.setFocusable(true);
                    showKeyboard(editText);
                    button.setText("수정완료");
                }
                else if (currentText.equals("수정완료")) {
                    button.setText("수정하기");
                }
            }
        });
    }

    private void showKeyboard(EditText editText) {
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }
}
