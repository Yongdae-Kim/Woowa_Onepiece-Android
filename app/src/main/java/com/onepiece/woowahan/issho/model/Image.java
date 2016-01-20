package com.onepiece.woowahan.issho.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class Image {
    @SerializedName("uri")
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
