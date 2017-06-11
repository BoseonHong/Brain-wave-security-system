package com.example.brainwavess.brain_wave_security_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    public EditText et_id, et_password, et_name;
    public Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, AuthEGGActivity.class);
                intent.putExtra("ID", et_id.getText().toString());
                intent.putExtra("NAME", et_name.getText().toString());
                intent.putExtra("PASSWORD", et_password.getText().toString());
                startActivity(intent);
            }
        });
    }
}
