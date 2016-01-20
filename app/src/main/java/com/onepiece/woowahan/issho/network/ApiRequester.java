package com.onepiece.woowahan.issho.network;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class ApiRequester {

    private static ApiRequester requester;

    private final String API_URI = "http://10.10.0.91:5050/api/";
    private final Retrofit retrofit;

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

    public void call(ApiCallback apiCallback) {
        ApiService service = retrofit.create(ApiService.class);
        apiCallback.callbackMethod(service);
    }
}



