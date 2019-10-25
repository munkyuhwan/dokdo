package com.ActivityList;

import android.widget.TabHost;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.dokdo.MainOnClickListener;
import com.dokdo.R;
import com.dokdo.TabViewMain;

import java.util.HashMap;
import java.util.Map;

public class ActivityListOnClickListener implements TabHost.OnTabChangeListener {

    private ActivityListMain mActivity;
    private TabHost mTabHost;

    protected ActivityListOnClickListener(TabHost tabHost, ActivityListMain activity) {
        this.mTabHost = tabHost;
        this.mActivity = activity;
    }

    protected MainOnClickListener registerTabhost() {
        this.mTabHost.setOnTabChangedListener(this);
        return null;
    }

    @Override
    public void onTabChanged(String tabId) {
        VolleyRequestClass volleyRequest = new VolleyRequestClass(mActivity, mActivity);
        Map<String, String> data = new HashMap<>();
        data.put("tags", EndPoints.GET_ACTIVITY_TAG);

        switch (tabId) {
            case "DAILY_CHART":
                data.put("tags", EndPoints.GET_ACTIVITY_TAG);
                volleyRequest.startSendData(data, EndPoints.GET_ACTIVITY_URL);
                //this.mActivity.getData(R.id.activity_list_1, "1");
                break;
            case "PERIOD_CHART":
                volleyRequest.startSendData(data, EndPoints.GET_ACTIVITY_URL);
                //this.mActivity.getData(R.id.activity_list_2, "2");
                break;

                default:
                    break;
        }

    }
}
