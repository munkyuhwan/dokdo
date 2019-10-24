package com.CustomLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.MyUtilities.DoLogin;
import com.dokdo.R;
import com.dokdo.TabViewMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements MainInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText id = (EditText)findViewById(R.id.login_id);
        EditText pw = (EditText)findViewById(R.id.login_pw);
        Button doLogin = (Button)findViewById(R.id.login_btn);

        final Map<String, String> data = new HashMap<>();
        data.put("id", id.getText().toString());
        data.put("pw", pw.getText().toString());
        data.put("tag", "login");

        doLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // VolleyRequestSingleton.getInstance().initVolley(LoginActivity.this, LoginActivity.this);
               // VolleyRequestSingleton.getInstance().startSendData(data, EndPoints.SELECT_LOGIN);

                VolleyRequestClass volley = new VolleyRequestClass(LoginActivity.this, LoginActivity.this);
                volley.startSendData(data, EndPoints.SELECT_LOGIN);

            }
        });

    }


    @Override
    public void responseResult(JSONArray jsonArray, String tag) {
        try {
            JSONObject job = jsonArray.getJSONObject(0);
            if (job.getBoolean("result")) {
                DoLogin doLogin = new DoLogin(this);
                if ( doLogin.saveLoginInfo(job.getString("id").toString(), job.getString("idx").toString(),this)) {
                    Intent intent = new Intent(this, TabViewMain.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, R.string.login_fail, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
