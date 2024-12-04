package com.example.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class register extends AppCompatActivity {
    private EditText acc, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        acc = findViewById(R.id.editTextAccount);
        psw = findViewById(R.id.editTextPsw);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = acc.getText().toString();
                String password = psw.getText().toString();

                if (account.isEmpty() || password.isEmpty()) {
                    Toast.makeText(register.this, "請輸入帳號密碼", Toast.LENGTH_SHORT).show();
                } else {
                    if (isAccountDuplicate(account)) {
                        Toast.makeText(register.this, "帳號已存在，請使用其他帳號", Toast.LENGTH_SHORT).show();
                    } else {
                        saveToFile(account, password);
                        Toast.makeText(register.this, "註冊成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean isAccountDuplicate(String account) {
        try (FileInputStream fis = openFileInput("login.txt");
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String storedAccount = parts[0].split(": ")[1];
                    if (storedAccount.equals(account)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void saveToFile(String account, String password) {
        String data = "Account: " + account + ", Password: " + password + "\n";
        try (FileOutputStream fos = openFileOutput("login.txt", Context.MODE_APPEND)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}