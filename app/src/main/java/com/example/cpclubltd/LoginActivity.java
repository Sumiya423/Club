package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnStu,btnAdmin;
    TextView textViewAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAdmin=findViewById(R.id.adminBtnId);
        btnStu=findViewById(R.id.stuBtnId);
        textViewAdmin=findViewById(R.id.TVAdminId);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,AdminLoginActivity.class);
                startActivity(intent);
            }
        });
        btnStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LoginActivity.this,MainActivityUser.class);
                startActivity(intent);
            }
        });


    }
}