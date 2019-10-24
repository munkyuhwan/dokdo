package com.CustomList;

import android.graphics.drawable.Drawable;

public class ListViewItem {

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;



    //메인 리스
    private long idx;
    private String shopName;
    private String addressFrom;
    private String addressNewFrom;
    private String attressTo;
    private String serialNum;
    private String timeLimit;
    private String paymentType;
    private String paymentAmt;
    private String deliveryFee;
    private String distance;
    private String request;

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressNewFrom() {
        return addressNewFrom;
    }

    public void setAddressNewFrom(String addressNewFrom) {
        this.addressNewFrom = addressNewFrom;
    }

    public String getAttressTo() {
        return attressTo;
    }

    public void setAttressTo(String attressTo) {
        this.attressTo = attressTo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(String paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }





    //공지사항 변
    private String title;
    private String content;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    //거래 내역 리스트 변수


    private String cashDate;
    private String cashShopName;
    private String cashNumber;
    private String cashType;
    private String cashAmt;
    private String cashTitle;

    public String getCashDate() {
        return cashDate;
    }
    public void setCashDate(String cashDate) {
        this.cashDate = cashDate;
    }
    public String getCashShopName() {
        return cashShopName;
    }
    public void setCashShopName(String cashShopName) {
        this.cashShopName = cashShopName;
    }
    public String getCashNumber() {
        return cashNumber;
    }
    public void setCashNumber(String cashNumber) {
        this.cashNumber = cashNumber;
    }
    public String getCashType() {
        return cashType;
    }
    public void setCashType(String cashType) {
        this.cashType = cashType;
    }
    public String getCashAmt() {
        return cashAmt;
    }
    public void setCashAmt(String cashAmt) {
        this.cashAmt = cashAmt;
    }
    public String getCashTitle() {
        return cashTitle;
    }
    public void setCashTitle(String cashTitle) {
        this.cashTitle = cashTitle;
    }

}
