package com.dokdo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TabHost;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.CustomList.CustomListView;
import com.CustomLogin.LoginActivity;
import com.DetailView.DetailView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.MyUtilities.MyHttpRequest.VolleyRequestSingleton;
import com.MyUtilities.DoLogin;
import com.MyUtilities.MyLocationUtil.LocationManagerUtil;
import com.MyUtilities.PermissionChecker;
import com.MyUtilities.VolleyRequestAsync;
import com.android.volley.VolleyLog;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TabViewMain extends AppCompatActivity implements MainInterface {
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PermissionChecker.getInstance()
                .init(this)
                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .addPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .getInstance().checkPermissions();

        registerTabButtons();
        registerNavigationMenu();
        registerMenuButtons();

        getData(R.id.list_view_1,"1");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        List<String> spinnerAccept = new ArrayList<>();
        spinnerAccept.add(EndPoints.UPDATE_LOCATION_TAG);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!VolleyRequestAsync.getInstance().isCancelled()) {
            Log.e("tag","=========================cancel volley async=========================");
            VolleyRequestAsync.getInstance().cancel(true);
        }
        //LocationManagerUtil.getInstance().dismissLocationUpdate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //LocationManagerUtil.getInstance().dismissLocationUpdate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag","=========================on resume=========================");

        if (VolleyRequestAsync.getInstance().isCancelled()) {
            Log.e("tag","=========================Restart volley async=========================");

        }
        /*
        if (LocationManagerUtil.getInstance().getManager() == null ) {
            LocationManagerUtil.getInstance()
                    .init(this, this)
                    .getLocationData(this);
        }

         */

    }

    private void registerNavigationMenu() {
        this.navigationView = (NavigationView) findViewById(R.id.nav_view1);
        new MainOnClickListener(this.navigationView, this).registerNaviMenu();
    }

    private void registerTabButtons() {

        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("관할 공유") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("배정 픽업") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("완료 취소") ;
        tabHost1.addTab(ts3) ;

        new MainOnClickListener(tabHost1, this).registerTabhost();
    }

    private void registerMenuButtons() {
        View headerLayout = navigationView.getHeaderView(0);
        RadioGroup rg = (RadioGroup)headerLayout.findViewById(R.id.radio_grp);
        new MainOnClickListener(rg, this).registerRadio();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DoLogin doLogin = new DoLogin(this);
        if (doLogin.loginCheck().get("userIdx").toString().equals("") ) {
            Intent toLogin = new Intent(this, LoginActivity.class);
            toLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(toLogin);
            finish();
        }
    }



    protected void updateStatus(String status) {
        //VolleyRequestSingleton.getInstance().initVolley(this, this);
        Map<String, String> data = new HashMap<>();
        data.put("tag","updat_status");
        data.put("status",status);
        data.put("idx","13");
        //VolleyRequestSingleton.getInstance().startSendData(data, EndPoints.UPDATE_STATUS);

        VolleyRequestClass volley = new VolleyRequestClass(this, this);
        volley.startSendData(data, EndPoints.UPDATE_STATUS);

    }

    protected void getData(int listId, String tabNum) {
        Log.e("tag","====tabNum: "+tabNum+"================================");
        Log.e("tag","====prev tabNum: "+VolleyRequestAsync.getInstance().getClassNum()+"================================");

        CustomListView listView =new CustomListView(this);
        Log.e("tag",EndPoints.GET_LIST_URL);
        listView.init(listId, EndPoints.GET_LIST_URL, EndPoints.ORDER_LIST_TAG, DetailView.class, tabNum);

        /*
        if ( tabNum.equals(VolleyRequestAsync.getInstance().getClassNum()) ) {
            VolleyRequestAsync.getInstance().init(this, R.id.list_view_1, DetailView.class, tabNum);
            VolleyRequestAsync.getInstance().execute();
        }else {
            VolleyRequestAsync.getInstance().setClassNum(tabNum);
        }

         */

        //VolleyRequestAsync.getInstance().init(this, R.id.list_view_1, DetailView.class, tabNum);
        //VolleyRequestAsync.getInstance().execute();


    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {
        Log.e("request reuslt", "===========================================");
        Log.e("request reuslt tag", tag);

        Log.e("request reuslt", String.valueOf(jsonArray));
        Log.e("request reuslt", "===========================================");

    }

}