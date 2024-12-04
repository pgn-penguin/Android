package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class login extends AppCompatActivity {
    private EditText acc, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button3 = findViewById(R.id.button3);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        acc = findViewById(R.id.editTextAccount);
        psw = findViewById(R.id.editTextPsw);

        button3.setOnClickListener(buttonOnClick);
        buttonRegister.setOnClickListener(buttonRegisterListener);
    }

    private Button.OnClickListener buttonOnClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            String inputAccount = acc.getText().toString();
            String inputPassword = psw.getText().toString();

            if (inputAccount.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(login.this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
            } else {
                // 檢查帳號密碼是否存在且正確
                if (checkCredentials(inputAccount, inputPassword)) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("account", inputAccount);
                    intent.putExtras(bundle);
                    setResult(11, intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "帳號或密碼錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private Button.OnClickListener buttonRegisterListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
        }
    };

    private boolean checkCredentials(String account, String password) {
        try (FileInputStream fis = openFileInput("login.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String storedAccount = parts[0].split(": ")[1];
                    String storedPassword = parts[1].split(": ")[1];
                    if (storedAccount.equals(account) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}