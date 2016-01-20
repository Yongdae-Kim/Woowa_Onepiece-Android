package com.onepiece.woowahan.issho.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class Location {
    @SerializedName("addr")
    private String addr;
    @SerializedName("lng")
    private String lng;
    @SerializedName("lat")
    private String lat;

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }
}
