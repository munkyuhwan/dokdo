package com.CustomList;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.MyUtilities.MyHttpRequest.VolleyRequestSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomListView implements MainInterface {

    private ListView mListView;
    CustomListAdapter adapter;

    private int currentPage = 0;

    private int lastPosition = 0;



    private Activity mAcitivty;
    public CustomListView(Activity activity) {
        this.mAcitivty = activity;
    }

    public void init(int listId, String listUrl, String listTag, Class detailView, String classNum) {
        initListView(listId, listUrl, listTag, detailView, classNum);
        getListData(listUrl, listTag, classNum);
    }

    protected Map<String, String> setSubmitData(String listTag, String classNum) {
        Map<String, String> data = new HashMap<>();

        data.put("tag",listTag);
        data.put("page",String.valueOf(currentPage));
        data.put("classNum", String.valueOf(classNum));
        data.put("user_idx","13");

        return data;
    }

    protected void getListData(String listUrl, String listTag, String classNum) {
        //VolleyRequestSingleton.getInstance().initVolley(mAcitivty, this);
        //VolleyRequestSingleton.getInstance().startSendData(setSubmitData(listTag), listUrl);

        VolleyRequestClass volley = new VolleyRequestClass(mAcitivty, this);
        volley.startSendData(setSubmitData(listTag,classNum), listUrl);
    }

    protected void initListView(int listId, final String listUrl, final String listTag, final Class detailView, final String classNum ){
        mListView = (ListView)mAcitivty.findViewById(listId);
        adapter = new CustomListAdapter();

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (mListView.getLastVisiblePosition() - mListView.getHeaderViewsCount() -
                        mListView.getFooterViewsCount()) >= (adapter.getCount() - 1)) {

                    lastPosition = mListView.getFirstVisiblePosition()+1;

                    currentPage++;
                    getListData(listUrl, listTag, classNum);

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        if (detailView != null) {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                    mAcitivty.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(mAcitivty, detailView);
                            intent.putExtra("id", String.valueOf(id));
                            mAcitivty.startActivity(intent);
                        }

                    });
                }
            });
        }

    }


    protected void setListView(JSONArray result, String tag) {

        mListView.setAdapter(adapter);

        for(int i=0;i<result.length();i++) {
            try {
                JSONObject obj = result.getJSONObject(i);
                adapter.addItem(obj.getLong("id"), obj, tag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mListView.setSelection(lastPosition);
        mListView.smoothScrollToPosition(lastPosition);

    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {

        Log.e("jsonarray", String.valueOf(jsonArray));
        //if (tag.equals("order_list")) {
            if (jsonArray.length() <= 0) {
                currentPage--;
            } else {
                setListView(jsonArray, tag);
            }
        //}
    }





}
