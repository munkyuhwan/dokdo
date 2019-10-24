package com.MyCashback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

import com.CustomList.CustomListView;
import com.DetailView.DetailView;
import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;
import com.MyUtilities.MyHttpRequest.VolleyRequestClass;
import com.MyUtilities.VolleyRequestAsync;
import com.dokdo.R;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MyCashback extends AppCompatActivity implements MainInterface {

    private TextView totalCash, bankName, bankOwner, bankAccount, commission, reductCash;
    private EditText requestAmt;
    private Button wholeAmt, requestBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cachback);

        totalCash = (TextView)findViewById(R.id.total_cash_amt);
        bankName = (TextView)findViewById(R.id.bank_name);
        bankOwner = (TextView)findViewById(R.id.account_owner);
        bankAccount = (TextView)findViewById(R.id.account_number);

        commission = (TextView)findViewById(R.id.commision_amt);
        reductCash = (TextView)findViewById(R.id.reduct_cash);

        requestAmt = (EditText)findViewById(R.id.request_amt);

        wholeAmt = (Button)findViewById(R.id.whole_amt);
        requestBtn = (Button)findViewById(R.id.request_btn);

        init();

    }


    private void init() {

        Map<String, String> data = new HashMap<>();
        data.put("tags", EndPoints.GET_MYCASHBACK_TAG);

        VolleyRequestClass volleyRequest = new VolleyRequestClass(this, this);
        volleyRequest.startSendData(data, EndPoints.GET_MYCASHBACK_URL);

        setTabs();
    }

    private void setTabs() {

        TabHost tabHost1 = (TabHost) findViewById(R.id.refund_tab);
        tabHost1.setup();

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("REQUEST");
        ts1.setContent(R.id.content_request);
        ts1.setIndicator("내 계좌 환급");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("TRANSFER");
        ts2.setContent(R.id.content_transfer);
        ts2.setIndicator("지점전송");
        tabHost1.addTab(ts2);

        new MyCashbackOnClickListener(tabHost1,this, this).registerTabhost();
    }

    protected void getData(int listId, String tabNum) {



    }

    @Override
    public void responseResult(JSONArray jsonArray, String tag) {

    }
}
