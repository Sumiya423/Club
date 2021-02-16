package com.example.cpclubltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUsername, etPassword;
    private TextView tvNeedHelp;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        initComps();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                if(username.equalsIgnoreCase("ADMIN") && password.equals("admin303")){
                    new SharedPrefs(getApplicationContext()).saveLoginInfo(username, password, true);

                    Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "INVALID USERNAME OR PASSWORD\nCLICK NEED HELP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminLoginActivity.this, "USERNAME: ADMIN\nPASSWORD: admin303", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initComps() {
        btnLogin = findViewById(R.id.login_btn_login);
        etUsername = findViewById(R.id.login_et_username);
        etPassword = findViewById(R.id.login_et_password);
        tvNeedHelp = findViewById(R.id.login_tv_needhelp);
        toolbar = findViewById(R.id.login_toolbar);
    }
}