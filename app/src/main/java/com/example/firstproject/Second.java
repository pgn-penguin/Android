package com.example.firstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(buttonOnClick);
    }

    private Button.OnClickListener buttonOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}