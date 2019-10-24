package com.BulletinBoard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.CustomList.CustomListView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.dokdo.R;

public class BulletinBoard extends AppCompatActivity  {

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board);

        mListView = (ListView)findViewById(R.id.bulletin_list);
        CustomListView listView =new CustomListView(this);
        listView.init(mListView.getId(), EndPoints.GET_BULLETIN_LIST, EndPoints.BULLETIN_LIST_TAG, BulletinDetailView.class, null);

    }

}
