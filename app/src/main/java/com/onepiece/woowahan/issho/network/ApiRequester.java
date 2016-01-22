package com.onepiece.woowahan.issho.network;

import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.model.BusStopModel;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class ApiRequester {

    private static ApiRequester requester;

    private final String API_URI = "http://10.10.0.91:5050/api/";
    private Retrofit retrofit;

    private ApiRequester() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URI).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiRequester getInstacne() {
        if (requester == null) {
            requester = new ApiRequester();
        }
        return requester;
    }

    public ApiService getService() {
        return retrofit.create(ApiService.class);
    }
}



