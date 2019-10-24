package com.CalculateBalance;

import android.app.Activity;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;

import java.util.HashMap;
import java.util.Map;

public class RequestBalanceData {

    private Activity mActivity;
    private MainInterface mMainInterface;

    public RequestBalanceData(Activity activity, MainInterface mainInterface) {
        this.mActivity = activity;
        this.mMainInterface = mainInterface;
    }

    protected void requestToServier(String startDate, String endDate) {

        Map<String, String> data = new HashMap<>();

        data.put("tags",EndPoints.GET_BALANCE_TAG);
        data.put("start_date",startDate);
        data.put("end_date",endDate);


        VolleyRequestClass volleyRequest = new VolleyRequestClass(mActivity,mMainInterface);
        volleyRequest.startSendData(data, EndPoints.GET_BALANCE_URL);

    }

}
