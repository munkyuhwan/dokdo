package com.MyAccountInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MyUtilities.MyAccountUtil;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.MyUtilities.MySharedPreference;
import com.dokdo.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class MyAccountInfo extends AppCompatActivity implements MainInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_info);

        init();
    }

    private void init() {
        VolleyRequestClass volleyRequest = new VolleyRequestClass(this,this);
        Map<String,String> data = new HashMap<>();
        data.put("tags",EndPoints.GET_MYACCOUNT_TAG);
        data.put("id", getID());
        volleyRequest.startSendData(data, EndPoints.GET_MYACCOUNT);
    }

    private String getID() {
        MyAccountUtil util = new MyAccountUtil(this);
        return util.getMyId().get("userIdx");
    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {

    }
}
