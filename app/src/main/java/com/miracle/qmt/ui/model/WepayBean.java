package com.miracle.qmt.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhouChao on 2017/9/14.
 */

public class WepayBean {
    /**
     * appid : wxc281922aa3bdbaa9
     * noncestr : i364po75ixm4mjwislluekwxl9vi4b4a
     * package : Sign=WXPay
     * timestamp : 1505371727
     * sign : 154A62947F4CDB245B20281DB8854ABC
     * partnerid : 1483880722
     * prepayid : wx2017091414484740b82a0d5c0288422461
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String timestamp;
    private String sign;
    private String partnerid;
    private String prepayid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
}
