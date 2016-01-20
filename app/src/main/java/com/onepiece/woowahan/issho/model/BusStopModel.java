package com.onepiece.woowahan.issho.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class BusStopModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("loc")
    private Location loc;
    @SerializedName("ads_cnt")
    private int adsCnt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getAdsCnt() {
        return adsCnt;
    }

    public void setAdsCnt(int adsCnt) {
        this.adsCnt = adsCnt;
    }
}