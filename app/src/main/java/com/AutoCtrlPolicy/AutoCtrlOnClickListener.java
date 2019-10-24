package com.AutoCtrlPolicy;

import android.widget.TabHost;

import com.dokdo.MainOnClickListener;
import com.dokdo.R;
import com.dokdo.TabViewMain;

public class AutoCtrlOnClickListener implements TabHost.OnTabChangeListener {

    private AutoCtrlPolicy mActivity;
    private TabHost mTabHost;

    protected AutoCtrlOnClickListener(TabHost tabHost, AutoCtrlPolicy activity) {
        this.mTabHost = tabHost;
        this.mActivity = activity;
    }
    protected MainOnClickListener registerTabhost() {
        this.mTabHost.setOnTabChangedListener(this);
        return null;
    }

    @Override
    public void onTabChanged(String tabId) {
        switch (tabId){
            case "PROGRESS":
                this.mActivity.getData(R.id.policy_list_view_1,"1");
                break;
            case "COMPLETE":
                this.mActivity.getData(R.id.policy_list_view_2,"2");
                break;
            case "SUSPENSION":
                this.mActivity.getData(R.id.policy_list_view_3,"3");
                break;
            default:
                break;
        }
    }
}
