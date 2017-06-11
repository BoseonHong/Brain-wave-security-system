package com.example.brainwavess.brain_wave_security_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AuthEGGActivity extends AppCompatActivity {

    private Button btn_reg;

    private String url = "http://192.168.33.10:5555/brainwave.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_egg);

        final Intent intent = getIntent();

        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regUser(intent);
                regEGG(intent, "1", "2", "3", "4", "5");
            }
        });

        //TODO 뇌파정보를 받아오기
    }

    private void regUser(Intent intent) {
        final String id = intent.getStringExtra("ID");
        final String name = intent.getStringExtra("NAME");
        final String password = intent.getStringExtra("PASSWORD");

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AuthEGGActivity.this,"등록 성공",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AuthEGGActivity.this,"등록 실패",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("DIST", "reguser");
                params.put("ID", id);
                params.put("NAME", name);
                params.put("PASSWORD", password);
                return params;
            }
        };

        requestQueue.add(stringReq);
    }

    private void regEGG(Intent intent, String  dddd, String tttt, String aaaa, String bbbb, String gggg) {
        final String id = intent.getStringExtra("ID");
        final String delta = dddd;
        final String theta = tttt;
        final String alpha = aaaa;
        final String beta = bbbb;
        final String gamma = gggg;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AuthEGGActivity.this,"등록 성공",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AuthEGGActivity.this,"등록 실패",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("DIST", "regegg");
                params.put("delta", delta);
                params.put("theta", theta);
                params.put("alpha", alpha);
                params.put("beta", beta);
                params.put("gamma", gamma);
                params.put("ID", id);
                return params;
            }
        };

        requestQueue.add(stringReq);
    }
}
