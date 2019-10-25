package com.ActivityList;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.dokdo.R;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class ActivityListMain extends AppCompatActivity implements MainInterface {
    private TextView orderCompleteCnt, pickupOverCnt, pickupAvgTime, pickupCompleteAvg, orderAvgDistance, orderAccDistance, pickupRequestComple, pickup20MinComple, pick40MinComple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        init();
    }

    private void init() {
        orderCompleteCnt    = (TextView)findViewById(R.id.order_complete_cnt);
        pickupOverCnt       = (TextView)findViewById(R.id.pickup_over_cnt);
        pickupAvgTime       = (TextView)findViewById(R.id.pick_avg_time);
        pickupCompleteAvg   = (TextView)findViewById(R.id.pickup_complete_avg);
        orderAvgDistance    = (TextView)findViewById(R.id.order_avg_distance);
        orderAccDistance    = (TextView)findViewById(R.id.order_acc_distance);
        pickupRequestComple = (TextView)findViewById(R.id.pickup_request_comple);
        pickup20MinComple   = (TextView)findViewById(R.id.pickup_20min_comple);
        pick40MinComple     = (TextView)findViewById(R.id.pickup_40min_comple);

        registerTabButtons();
        VolleyRequestClass volleyRequest = new VolleyRequestClass(this, this);
        Map<String, String> data = new HashMap<>();
        data.put("tags", EndPoints.GET_ACTIVITY_TAG);
        volleyRequest.startSendData(data, EndPoints.GET_ACTIVITY_URL);

    }

    private void registerTabButtons() {

        TabHost tabHost1 = (TabHost) findViewById(R.id.activity_tab);
        tabHost1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("DAILY_CHART");
        ts1.setContent(R.id.tab_1);
        ts1.setIndicator("일별 차트");
        tabHost1.addTab(ts1);

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("PERIOD_CHART");
        ts2.setContent(R.id.tab_2);
        ts2.setIndicator("기간 차트");
        tabHost1.addTab(ts2);

        new ActivityListOnClickListener(tabHost1, this).registerTabhost();




    }


    @Override
    public void responseResult(JSONArray jsonArray, String tag) {
        Log.e("response","===============response=======================");
        try {
            Log.e("response", String.valueOf(jsonArray.getJSONObject(0)));

            orderCompleteCnt.setText(jsonArray.getJSONObject(0).getString("order_complete_cnt"));
            pickupOverCnt.setText(jsonArray.getJSONObject(0).getString("over_order_cnt"));
            pickupAvgTime.setText(jsonArray.getJSONObject(0).getString("pickup_avg"));
            pickupCompleteAvg.setText(jsonArray.getJSONObject(0).getString("pickup_complete_avg"));
            orderAvgDistance.setText(jsonArray.getJSONObject(0).getString("order_avg_distance"));
            orderAccDistance.setText(jsonArray.getJSONObject(0).getString("order_acc_distance"));
            pickupRequestComple.setText(jsonArray.getJSONObject(0).getString("pickup_request_complete"));
            pickup20MinComple.setText(jsonArray.getJSONObject(0).getString("pickup_20min"));
            pick40MinComple.setText(jsonArray.getJSONObject(0).getString("pickup_40min"));
        }catch(Exception e) {

        }
    }


}
