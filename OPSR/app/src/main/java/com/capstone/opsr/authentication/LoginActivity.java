package com.capstone.opsr.authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.opsr.R;
import com.capstone.opsr.home.MainActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin ;
    TextView textRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        // Demo run login in app by TrinhLVSE05062
        btnLogin = findViewById(R.id.btnLogin);
        textRegister = findViewById(R.id.btnRegister);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Open Main app", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Open Register Activity", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
