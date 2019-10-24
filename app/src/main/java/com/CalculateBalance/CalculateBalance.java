package com.CalculateBalance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.dokdo.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class CalculateBalance extends AppCompatActivity implements MainInterface {

    private RadioGroup radioGroup;
    private RadioButton today, yesterday, week, month;

    private TextView completeCnt, commissionCnt, afterCommission, jingsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_balance);

        radioGroup = (RadioGroup) findViewById(R.id.period_radio_grp);
        today = (RadioButton) findViewById(R.id.radio_today);
        yesterday = (RadioButton) findViewById(R.id.radio_yesterday);
        week = (RadioButton) findViewById(R.id.radio_week);
        month = (RadioButton) findViewById(R.id.radio_month);

        radioGroup.setOnCheckedChangeListener(new BalancePeriodSelect(this, this));
        today.setChecked(true);

        completeCnt = (TextView)findViewById(R.id.total_complete_cnt);
        commissionCnt = (TextView)findViewById(R.id.total_commision);
        afterCommission = (TextView)findViewById(R.id.total_after_commission);
        jingsu = (TextView)findViewById(R.id.total_jingsu);

    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {
        Log.e("balance", "============================");
        Log.e("balance", String.valueOf(jsonArray));

        try {
            completeCnt.setText( String.valueOf(jsonArray.getJSONObject(0).getInt("complete_cnt")) );
            commissionCnt.setText( String.valueOf(jsonArray.getJSONObject(0).getInt("commision_amt")) );
            afterCommission.setText( String.valueOf(jsonArray.getJSONObject(0).getInt("after_commission")) );
            jingsu.setText( String.valueOf(jsonArray.getJSONObject(0).getInt("jingsu")) );

        }catch (Exception e) {

        }


    }
}
