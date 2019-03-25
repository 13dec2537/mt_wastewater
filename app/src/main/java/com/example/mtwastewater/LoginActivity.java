package com.example.mtwastewater;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Method;

import static com.example.mtwastewater.Constant.PASS;
import static com.example.mtwastewater.Constant.USER;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Core core;
    private Spinner stateNetwork;
    private EditText edUser,edPass;
    private Button btnLogin;
    private InitSharedPreferences initSh;
    private SharedPreferences sh;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        core = new Core(this);
        editor = core.initShared();
        stateNetwork = findViewById(R.id.StateNetwork);
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        initSh = new InitSharedPreferences(this);
        sh = initSh.initShared();
        editor = initSh.initEditor();
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isNetworkConnected = core.isNetworkConnected();
        stateNetwork.setEnabled(isNetworkConnected);
        core.Login("mongkol","1234");
//        edUser.setHint(isNetworkConnected ? "ชื่อผู้ใช้" : "รหัสพนักงาน");
////        edPass.setHint(isNetworkConnected ? "รหัสผ่าน" : "รหัสพนักงาน");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                editor.putString(USER,edUser.getText().toString());
                editor.putString(PASS,edPass.getText().toString());
                editor.commit();
                core.Login(edUser.getText().toString(),edPass.getText().toString());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateNetwork != null) {
            try {
                Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
                method.setAccessible(true);
                method.invoke(stateNetwork);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(sh.contains(USER))
            edUser.setText(sh.contains(USER) ? (sh.getString(USER,"")) : "" );
            edUser.setSelection(edUser.getText().length());
            edPass.setText(sh.contains(PASS) ? (sh.getString(PASS,"")) : "" );
    }
}
