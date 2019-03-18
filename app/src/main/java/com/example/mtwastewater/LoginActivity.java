package com.example.mtwastewater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Core core;
    private Spinner stateNetwork;
    private EditText edUser,edPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        core = new Core(this);
        stateNetwork = findViewById(R.id.StateNetwork);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isNetworkConnected = core.isNetworkConnected();
        Log.d("LOG", String.valueOf(isNetworkConnected));
        stateNetwork.setEnabled(isNetworkConnected);
        edUser.setHint(isNetworkConnected ? "ชื่อผู้ใช้" : "รหัสพนักงาน");
        edPass.setHint(isNetworkConnected ? "รหัสผ่าน" : "รหัสพนักงาน");


        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                core.Login(edUser.getText().toString(),edPass.getText().toString());
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateNetwork.clearDisappearingChildren();
    }
}
