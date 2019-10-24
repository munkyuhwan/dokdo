package com.MyCashList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.CustomList.CustomListView;
import com.DetailView.DetailView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.VolleyRequestAsync;
import com.dokdo.R;

public class MyCashList extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cash_list);

        //this.mListView = (ListView) findViewById(R.id.cash_detail_list);

        getData(R.id.cash_detail_list, null);

    }

    protected void getData(int listId, String tabNum) {
        Log.e("tag", "====tabNum: " + tabNum + "================================");
        Log.e("tag", "====prev tabNum: " + VolleyRequestAsync.getInstance().getClassNum() + "================================");

        CustomListView listView = new CustomListView(this);
        listView.init(listId, EndPoints.GET_MYCASH_URL, EndPoints.GET_MYCASH_TAG, null, tabNum);



    }


}