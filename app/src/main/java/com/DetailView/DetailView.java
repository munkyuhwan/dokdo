package com.DetailView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyMultipartRequest;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.dokdo.R;

import org.json.JSONArray;

public class DetailView extends AppCompatActivity implements MainInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Log.e("bundle",getIntent().getExtras().getString("id"));


        VolleyRequestClass volleyRequest = new VolleyRequestClass(this, this);
        volleyRequest.startSendData(null, EndPoints.GET_LIST_URL+"/"+getIntent().getExtras().getString("id"));



    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {
        Log.e("tag","response result================================");
        Log.e("ag","result: "+jsonArray);
    }
}
