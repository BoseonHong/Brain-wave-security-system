package com.example.brainwavess.brain_wave_security_system;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText et_id, et_password;
    private CheckBox checkBox;
    private Button btn_login, btn_signup;

//    private ArrayAdapter adapter;

    private String url = "http://192.168.33.10:5555/brainwave.php";

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        sharedPref = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        boolean check = sharedPref.getBoolean("Checkbox", false);
//        if (check) {
//            String  id = sharedPref.getString("Id", "");
//
//            Intent intent = new Intent(MainActivity.this, LockLoginActivity.class);
//            intent.putExtra("ID", id);
//            startActivity(intent);
//        }

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_id.getText().toString() != null && et_password.getText().toString() != null) {
                    loginVolly();

                    if (checkBox.isChecked()) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putBoolean("Checkbox", true);
                        editor.putString("Id", et_id.getText().toString());
                        editor.apply();
                    }
                }
            }
        });

        btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginVolly() {
        //queue
        RequestQueue postQueue = Volley.newRequestQueue(this);

        StringRequest stringReq=new StringRequest(Request.Method.POST, url,

                //전송 성공
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responce) {
                        Toast.makeText(MainActivity.this, "전송 성공", Toast.LENGTH_SHORT).show();
                        Log.v("Response : ", responce);
                        try {
                            //
                            JSONObject jsonobject = new JSONObject(responce);
                            if (jsonobject.getInt("status") == 1) {
//                                JSONObject result = jsonobject.getJSONObject("result");
//                                JSONArray userList = result.getJSONArray("SQL_TEST");
                                Log.v("Status : ", "Successed!!!!!!!!!!!!!!!!!!!!!");
                                Intent intent = new Intent(MainActivity.this, SuccessLogin.class);
                                startActivity(intent);
                            }
//                            JSONArray userList = jsonobject.getJSONArray("SQL_TEST");

                        } catch (JSONException e) {
                            Log.v("JSONerror : ", e.getMessage());
                        }
                    }
                },

                //전송 실패
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MainActivity.this,"전송 실패",Toast.LENGTH_SHORT).show();
                    }
                }){

            //전송할 데이터 설정
            @Override
            protected Map<String,String> getParams(){
                //id, 이름, 페스워드 설정
                Map<String, String> params = new HashMap<String, String>();
                params.put("DIST", "login");
                params.put("ID", et_id.getText().toString());
                params.put("PASSWORD", et_password.getText().toString());
                return params;
            }
        };

        postQueue.add(stringReq);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
