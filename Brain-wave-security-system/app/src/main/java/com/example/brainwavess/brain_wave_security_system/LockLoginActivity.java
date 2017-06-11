package com.example.brainwavess.brain_wave_security_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LockLoginActivity extends AppCompatActivity {

    EditText et_password;
    Button btn_login;

    private String url = "http://192.168.33.10:5555/brainwave.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_login);

        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 뇌파정보를 받고 인증하기
                checkEGG();
            }
        });
    }

    private void checkEGG() {
        
    }
}
