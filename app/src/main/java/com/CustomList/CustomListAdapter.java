package com.CustomList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.MyUtilities.MyHttpRequest.EndPoints;
import com.MyUtilities.EtcMethods;
import com.dokdo.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listViewItemList.get(position).getIdx();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        ListViewItem listViewItem = listViewItemList.get(position);

        if (listViewItem.getTag().toString().equals(EndPoints.ORDER_LIST_TAG)) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item, parent, false);
            }

            TextView shopName = (TextView) convertView.findViewById(R.id.shop_name);
            TextView addressFrom = (TextView) convertView.findViewById(R.id.address_from);
            TextView addressNewFrom = (TextView) convertView.findViewById(R.id.address_new_from);
            TextView serialNum = (TextView) convertView.findViewById(R.id.serial_num);
            TextView timeLimit = (TextView) convertView.findViewById(R.id.time_limit);
            TextView paymentType = (TextView) convertView.findViewById(R.id.payment_type);
            TextView paymentAmt = (TextView) convertView.findViewById(R.id.payment_amt);
            TextView deliveryFee = (TextView) convertView.findViewById(R.id.delivery_fee);
            TextView distance = (TextView) convertView.findViewById(R.id.distance);
            TextView request = (TextView) convertView.findViewById(R.id.request);


            shopName.setText(listViewItem.getShopName());
            addressFrom.setText(listViewItem.getAddressFrom());
            addressNewFrom.setText(listViewItem.getAddressNewFrom());
            serialNum.setText("#" + listViewItem.getSerialNum());
            timeLimit.setText(listViewItem.getTimeLimit() + "분");
            paymentType.setText(listViewItem.getPaymentType());
            paymentAmt.setText(EtcMethods.getInstance().NumberComma(listViewItem.getPaymentAmt()) + "원");
            deliveryFee.setText(EtcMethods.getInstance().NumberComma(listViewItem.getDeliveryFee()) + "원");
            distance.setText(listViewItem.getDistance() + "km");
            request.setText(listViewItem.getRequest());
        }else if (listViewItem.getTag().toString().equals(EndPoints.BULLETIN_LIST_TAG)) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item_bulletin, parent, false);
            }
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView bulletinTitle = (TextView) convertView.findViewById(R.id.bulletin_title);

            date.setText(listViewItem.getDate().toString());
            bulletinTitle.setText(listViewItem.getTitle().toString());
        }else if (listViewItem.getTag().toString().equals(EndPoints.SAFETY_LIST_TAG)) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item_safety, parent, false);
            }
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView bulletinTitle = (TextView) convertView.findViewById(R.id.safety_title);

            date.setText(listViewItem.getDate().toString());
            bulletinTitle.setText(listViewItem.getTitle().toString());
        }



        return convertView;
    }

    public void addItem(long idx, JSONObject values, String tag) {

        if (tag.equals(EndPoints.ORDER_LIST_TAG) ) {
            ListViewItem item = new ListViewItem();
            item.setIdx(idx);
            try {
                item.setTag(tag);

                item.setShopName(values.getString("shop_name"));
                item.setAddressFrom(values.getString("address_from"));
                item.setAddressNewFrom(values.getString("address_new_from"));
                item.setAttressTo(values.getString("address_to"));
                item.setSerialNum(values.getString("serial_num"));
                item.setTimeLimit(values.getString("time_limit"));
                item.setPaymentType(values.getString("payment_type"));
                item.setPaymentAmt(values.getString("payment_amt"));
                item.setDeliveryFee(values.getString("delivery_fee"));
                item.setDistance(values.getString("distance"));
                item.setRequest(values.getString("request"));
                listViewItemList.add(item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (tag.equals(EndPoints.BULLETIN_LIST_TAG)) {
            ListViewItem item = new ListViewItem();
            item.setIdx(idx);
            try {
                item.setTag(tag);

                item.setTitle(values.getString("title"));
                item.setContent(values.getString("content"));
                item.setDate(values.getString("date"));
                listViewItemList.add(item);
            }catch (Exception e) {

            }
        }
        else if (tag.equals(EndPoints.SAFETY_LIST_TAG)) {
            ListViewItem item = new ListViewItem();
            item.setIdx(idx);
            try {
                item.setTag(tag);

                item.setTitle(values.getString("title"));
                item.setContent(values.getString("content"));
                item.setDate(values.getString("date"));
                listViewItemList.add(item);
            }catch (Exception e) {

            }
        }
    }

}
