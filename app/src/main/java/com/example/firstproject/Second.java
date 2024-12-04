package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView3=(TextView)findViewById(R.id.textView3);
        TextView textView9=(TextView)findViewById(R.id.textView9);
        Button button = (Button) findViewById(R.id.button);
        Button button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(buttonOnClick);
        button4.setOnClickListener(buttonOnClick4);

        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        String name=bundle.getString("country");
        String s=name;
        textView3.setText(s);

        String a=new String(textView3.getText().toString());
        String b=new String("你選擇了：美國 ");
        String c=new String("你選擇了：日本 ");
        String d=new String("你選擇了：中國 ");
        String e=new String("你選擇了：韓國 ");
        String f=new String("你選擇了：泰國 ");
        if(a.equalsIgnoreCase(b)==true){
            textView9.setText("往返機票費用為\nNT$22450");
        }
        else if(a.equalsIgnoreCase(c)==true){
            textView9.setText("往返機票費用為\nNT$15000");
        }
        else if(a.equalsIgnoreCase(d)==true){
            textView9.setText("往返機票費用為\nNT$10000");
        }
        else if(a.equalsIgnoreCase(e)==true){
            textView9.setText("往返機票費用為\nNT$9800");
        }
        else if(a.equalsIgnoreCase(f)==true){
            textView9.setText("往返機票費用為\nNT$9500");
        }
    }
    private Button.OnClickListener buttonOnClick4 = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            setResult(22,intent);
            finish();
        }
    };

    private Button.OnClickListener buttonOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(Second.this, MainActivity.class);
            Bundle bundle=new Bundle();


            setResult(23,intent);
            finish();
        }
    };
}
