package com.CalculateBalance;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.MyUtilities.MyHttpRequest.Interfaces.MainInterface;

public class BalancePeriodSelect implements RadioGroup.OnCheckedChangeListener {

    private Activity mActivity;
    private MainInterface mMainInterface;


    public BalancePeriodSelect(Activity activity, MainInterface mainInterface) {
        this.mActivity = activity;
        this.mMainInterface = mainInterface;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RequestBalanceData requestBalance = new RequestBalanceData(mActivity, mMainInterface);
        requestBalance.requestToServier("2019-01-01","2019-12-12");
    }
}
