package com.example.firstproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//test
public class MainActivity extends AppCompatActivity {
    private EditText editTextText;
    private EditText editTextText2;
    private TextView textView;
    private TextView textView2;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextText=(EditText)findViewById(R.id.editTextText);
        editTextText2=(EditText)findViewById(R.id.editTextText2);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        button.setOnClickListener(buttonTranListener);
        button2.setOnClickListener(buttonTranListener2);
        button3.setOnClickListener(buttonTranListener3);
        button4.setOnClickListener(buttonTranListener4);
        button5.setOnClickListener(resetButtonListener);
        toggleButton.setOnClickListener(toggleButtonListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private Button.OnClickListener buttonTranListener=new Button.OnClickListener(){
        public void onClick(View v) {
            double num = Double.parseDouble(editTextText.getText().toString());
            double num2 = Double.parseDouble(editTextText2.getText().toString());
            double num3 = num + num2;
            textView.setText(""+num3);
            textView2.setText("+");
            Toast toast = Toast.makeText(MainActivity.this, "計算成功", Toast.LENGTH_SHORT);
            toast.show();
        }
    };
    private Button.OnClickListener buttonTranListener2=new Button.OnClickListener(){
        public void onClick(View v) {
            double num = Double.parseDouble(editTextText.getText().toString());
            double num2 = Double.parseDouble(editTextText2.getText().toString());
            double num3 = num - num2;
            textView.setText(""+num3);
            textView2.setText("-");
            Toast toast = Toast.makeText(MainActivity.this, "計算成功", Toast.LENGTH_SHORT);
            toast.show();
        }
    };
    private Button.OnClickListener buttonTranListener3=new Button.OnClickListener(){
        public void onClick(View v) {
            double num = Double.parseDouble(editTextText.getText().toString());
            double num2 = Double.parseDouble(editTextText2.getText().toString());
            double num3 = num * num2;
            textView.setText(""+num3);
            textView2.setText("*");
            Toast toast = Toast.makeText(MainActivity.this, "計算成功", Toast.LENGTH_SHORT);
            toast.show();
        }
    };
    private Button.OnClickListener buttonTranListener4=new Button.OnClickListener(){
        public void onClick(View v) {
            double num = Double.parseDouble(editTextText.getText().toString());
            double num2 = Double.parseDouble(editTextText2.getText().toString());
            double num3 = num / num2;
            textView.setText(""+num3);
            textView2.setText("/");
            Toast toast = Toast.makeText(MainActivity.this, "計算成功", Toast.LENGTH_SHORT);
            toast.show();
        }
    };

    private Button.OnClickListener resetButtonListener=new Button.OnClickListener(){
        public void onClick(View v) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要清除嗎?")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editTextText.setText("");
                            editTextText2.setText("");
                            textView.setText("結果");
                            textView2.setText("");
                            Toast toast = Toast.makeText(MainActivity.this, "已清除", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    };

    private ToggleButton.OnClickListener toggleButtonListener=new ToggleButton.OnClickListener(){
        public void onClick(View v) {
            if(toggleButton.isChecked()){
                button.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }else{
                button.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
            }
        }
    };
}