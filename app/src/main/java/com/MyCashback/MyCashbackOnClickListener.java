package com.MyCashback;

import android.widget.TabHost;

import com.MyUtilities.MyAccountUtil;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.dokdo.MainOnClickListener;

import java.util.HashMap;
import java.util.Map;

public class MyCashbackOnClickListener implements TabHost.OnTabChangeListener {

    private MyCashback mActivity;
    private TabHost mTabHost;
    private MainInterface mainInterface;


    protected MyCashbackOnClickListener(TabHost tabHost, MyCashback activity, MainInterface mainInterface) {
        this.mTabHost = tabHost;
        this.mActivity = activity;
        this.mainInterface = mainInterface;
    }

    protected MainOnClickListener registerTabhost() {
        this.mTabHost.setOnTabChangedListener(this);
        return null;
    }

    private void reqeustData(String url, String tag) {
        MyAccountUtil accountUtil = new MyAccountUtil(mActivity);
        Map<String, String> accountHash = accountUtil.getMyId();
        String userID = accountHash.get("userIdx");

        Map<String, String> data = new HashMap<>();
        data.put("tags", url);
        data.put("id",userID);

        VolleyRequestClass volleyRequest = new VolleyRequestClass(mActivity,this.mainInterface);
        volleyRequest.startSendData(data, tag);

    }

    @Override
    public void onTabChanged(String tabId) {
        switch (tabId){
            case "REQUEST":
                //this.mActivity.getData(R.id.list_view_1,"1");
                reqeustData(EndPoints.GET_MYCASHBACK_URL, EndPoints.GET_MYCASHBACK_TAG);
                break;
            case "TRANSFER":
                //this.mActivity.getData(R.id.list_view_2,"2");
                reqeustData(EndPoints.GET_MYTRASNFER_URL, EndPoints.GET_MYTRASNFER_TAG);
                break;
            default:
                break;
        }
    }
}
