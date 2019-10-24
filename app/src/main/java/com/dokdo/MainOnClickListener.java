package com.dokdo;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;

import androidx.annotation.NonNull;

import com.BulletinBoard.BulletinBoard;
import com.DetailView.DetailView;
import com.MyAccountInfo.MyAccountInfo;
import com.MyCashback.MyCashback;
import com.MyUtilities.VolleyRequestAsync;
import com.SafetyManual.SafetyManual;
import com.google.android.material.navigation.NavigationView;

public class MainOnClickListener implements NavigationView.OnNavigationItemSelectedListener, DialogInterface.OnClickListener, RadioGroup.OnCheckedChangeListener, TabHost.OnTabChangeListener {

    private RadioGroup mRadio;
    private Button mButton;
    private TabViewMain mActivity;
    private TabHost mTabHost;
    private NavigationView mNaviMenu;

    //라디오 버튼 리스너
    protected MainOnClickListener(RadioGroup radioGroup, TabViewMain activity) {
        this.mRadio = radioGroup;
        this.mActivity = activity;
    }
    protected MainOnClickListener registerRadio() {
        this.mRadio.setOnCheckedChangeListener(this);
        return null;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.on_duty:
                mActivity.updateStatus("1");
                changeTabChangeApi("1");
                break;
            case R.id.on_rest:
                mActivity.updateStatus("2");
                changeTabChangeApi("2");
                break;
            case R.id.off_duty:
                mActivity.updateStatus("3");
                changeTabChangeApi("3");
                break;
            default:
                break;
        }
    }

    private void changeTabChangeApi(String tabNum) {

        VolleyRequestAsync.getInstance().init(mActivity, R.id.list_view_1, DetailView.class, tabNum);
        VolleyRequestAsync.getInstance().execute();
    }




    //버튼 클릭 리스나

    protected MainOnClickListener(Button mButton, TabViewMain activity) {
        this.mButton = mButton;
        this.mActivity = activity;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }




    //탭선택 리스너
    protected MainOnClickListener(TabHost tabHost, TabViewMain activity) {
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
            case "Tab Spec 1":
                this.mActivity.getData(R.id.list_view_1,"1");
                break;
            case "Tab Spec 2":
                this.mActivity.getData(R.id.list_view_2,"2");
                break;
            case "Tab Spec 3":
                this.mActivity.getData(R.id.list_view_3,"3");
                break;
            default:
                break;
        }
    }




    //탭선택 리스너
    protected MainOnClickListener(NavigationView naviMenu, TabViewMain activity) {
        this.mNaviMenu = naviMenu;
        this.mActivity = activity;
    }

    protected MainOnClickListener registerNaviMenu() {
        this.mNaviMenu.setNavigationItemSelectedListener(this);
        return null;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;

        switch (menuItem.getItemId()) {
            case R.id.category_1_item_1:
                intent = new Intent(this.mActivity, BulletinBoard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                this.mActivity.startActivity(intent);
                break;
            case R.id.category_1_item_2:
                intent = new Intent(this.mActivity, SafetyManual.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                this.mActivity.startActivity(intent);
                break;
            case R.id.category_2_item_1:
                intent = new Intent(this.mActivity, MyAccountInfo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                this.mActivity.startActivity(intent);
                break;
            case R.id.category_2_item_2:
                intent = new Intent(this.mActivity, MyCashback.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                this.mActivity.startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }
}
