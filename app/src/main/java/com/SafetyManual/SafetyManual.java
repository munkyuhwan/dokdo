package com.SafetyManual;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.CustomList.CustomListView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.dokdo.R;

public class SafetyManual extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_manual);

        mListView = (ListView)findViewById(R.id.safety_maual_list);
        CustomListView listView =new CustomListView(this);
        listView.init(mListView.getId(), EndPoints.GET_SAFETY_LIST, EndPoints.SAFETY_LIST_TAG, SafetyDetail.class, null );

    }
}
