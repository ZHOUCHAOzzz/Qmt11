package com.miracle.qmt.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhouChao on 2017/9/14.
 */

public class PayBean {
    /**
     * appId : wxc281922aa3bdbaa9
     * nonceStr : ieim7xj1fhjtovp4jr4ybgg83mg5qa0m
     * package : prepay_id=wx2017091412511610cb07b95b0124262775
     * signType : MD5
     * timeStamp : 1505364676
     * paySign : 89ED8DC1CC836C7E6DB292EA067F1FF2
     * partnerid : 1483880722
     * prepay_id : wx2017091412511610cb07b95b0124262775
     */

    private String appId;
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    private String signType;
    private String timeStamp;
    private String paySign;
    private String partnerid;
    private String prepay_id;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}
