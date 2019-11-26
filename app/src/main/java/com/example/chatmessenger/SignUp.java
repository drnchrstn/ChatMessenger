package com.example.chatmessenger;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatmessenger.ui.dashboard.ContactDb;
import com.example.chatmessenger.ui.dashboard.ContactProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp extends AppCompatActivity {

    EditText EditName, EditPassword, EditConfirm;
    Button BtnSignup;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupview);
        EditName = findViewById(R.id.EditName);
        EditPassword = findViewById(R.id.EditPassword);
        EditConfirm = findViewById(R.id.EditConfirm);
        BtnSignup = findViewById(R.id.BtnSignup);
        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }


    public void signUp(){
        String date = getDateTime();
        String username = EditName.getText().toString();
        String password = EditPassword.getText().toString();
        String confirmPass = EditConfirm.getText().toString();

        if (username == null || username.length() <= 8){
            Toast.makeText(SignUp.this, "Invalid input", Toast.LENGTH_SHORT).show();
        }else if (password == null || password.length() <= 8){
            Toast.makeText(SignUp.this, "Invalid input", Toast.LENGTH_SHORT).show();
        } else{
            if (password != confirmPass){
                ContentValues newValues = new ContentValues();
                newValues.put(ContactDb.ROW_USERNAME, username);
                newValues.put(ContactDb.ROW_PASSWORD, password);
                newValues.put(ContactDb.ROW_DATE, date);
                getContentResolver().insert(ContactProvider.CONTENT_URI, newValues);
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(SignUp.this, "Inserted", Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(SignUp.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
