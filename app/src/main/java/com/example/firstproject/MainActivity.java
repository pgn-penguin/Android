package com.example.firstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private RadioGroup radioGroup1;
    private TextView textView2, textView4;
    private Spinner spinner;
    String[] Countrys = new String[]{"(請選擇)", "美國", "日本", "中國", "韓國", "泰國"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton) findViewById(R.id.radioButton5);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView4 = (TextView) findViewById(R.id.textView4);
        spinner = (Spinner) findViewById(R.id.spinner);
        radioGroup1.setOnCheckedChangeListener(radioGroup1Listener);
        spinner.setOnItemSelectedListener(spinnerListener);


        ArrayAdapter<String> Country = new ArrayAdapter<String>(this, R.layout.spinner_selected_shape, Countrys);
        Country.setDropDownViewResource(R.layout.spinner_dropdown_shape);
        spinner.setAdapter(Country);

    }
    private RadioGroup.OnCheckedChangeListener radioGroup1Listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.radioButton1) {
                textView2.setText("這五個國家當中最想去美國旅遊");
            } else if (checkedId == R.id.radioButton2) {
                textView2.setText("這五個國家當中最想去日本旅遊");
            } else if (checkedId == R.id.radioButton3) {
                textView2.setText("這五個國家當中最想去中國旅遊");
            } else if (checkedId == R.id.radioButton4) {
                textView2.setText("這五個國家當中最想去韓國旅遊");
            } else if (checkedId == R.id.radioButton5) {
                textView2.setText("這五個國家當中最想去泰國旅遊");
            }
        }
    };
    private Spinner.OnItemSelectedListener spinnerListener = new Spinner.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String str = parent.getSelectedItem().toString();
            if (str == "(請選擇)") {
                textView4.setText("");
            } else {
                textView4.setText("最想去旅遊的國家是" + str);
            }

        }

        public void onNothingSelected(AdapterView<?> parent) {
            textView4.setText("");
        }
    };
}
