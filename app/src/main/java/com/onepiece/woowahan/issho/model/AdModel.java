package com.onepiece.woowahan.issho.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class AdModel implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("cd_id")
    private int cdId;
    @SerializedName("title")
    private String title;
    @SerializedName("start_dt")
    private Date startDt;
    @SerializedName("end_dt")
    private Date endDt;
    @SerializedName("content1")
    private String content1;
    @SerializedName("content2")
    private String content2;
    @SerializedName("loc")
    private Location loc;
    @SerializedName("imgs")
    private List<Image> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDt() {
        return startDt;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public Date getEndDt() {
        return endDt;
    }

    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
