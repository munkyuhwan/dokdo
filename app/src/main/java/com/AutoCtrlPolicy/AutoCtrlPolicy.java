package com.AutoCtrlPolicy;

import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import com.CustomList.CustomListView;
import com.DetailView.DetailView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.VolleyRequestAsync;
import com.dokdo.R;

public class AutoCtrlPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_ctrl_polocy);

        registerTabButtons();
        getData(R.id.policy_list_view_1, "1");
    }


    private void registerTabButtons() {

        TabHost tabHost1 = (TabHost) findViewById(R.id.auto_tab_host) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("PROGRESS") ;
        ts1.setContent(R.id.complete_tab) ;
        ts1.setIndicator("진행중") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("COMPLETE") ;
        ts2.setContent(R.id.progress_tab) ;
        ts2.setIndicator("완료") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("SUSPENSION") ;
        ts3.setContent(R.id.sus_tab) ;
        ts3.setIndicator("중단") ;
        tabHost1.addTab(ts3) ;

        new AutoCtrlOnClickListener(tabHost1, this).registerTabhost();
    }

    protected void getData(int listId, String tabNum) {
        Log.e("tag","====tabNum: "+tabNum+"================================");
        Log.e("tag","====prev tabNum: "+ VolleyRequestAsync.getInstance().getClassNum()+"================================");

        CustomListView listView =new CustomListView(this);
        listView.init(listId, EndPoints.GET_AUTOPOLICY_URL, EndPoints.GET_AUTOPOLICY_TAG, null, tabNum);

    }
}
