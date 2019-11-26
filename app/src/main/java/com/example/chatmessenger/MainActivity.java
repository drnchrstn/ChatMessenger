package com.example.chatmessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText EditName, EditPassword;
    private Button BtnLogin, BtnSignup;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditPassword = findViewById(R.id.EditConfirm);
        EditName = findViewById(R.id.EditName);
        BtnLogin = findViewById(R.id.BtnLogin);
        BtnSignup = findViewById(R.id.BtnSignup);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });
        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
        dbHelper = new DbHelper(MainActivity.this);



    }


    public void LogIn(){
        if (EditName.getText() == null || EditName.getText().length() <= 5){
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }else if (EditPassword.getText() == null || EditPassword.getText().length() <= 5){
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Valid", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, FrontPage.class);
            startActivity(intent);
        }
    }


    public void SignUp(){
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }
}
