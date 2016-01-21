package com.onepiece.woowahan.issho.network;

import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.model.BusStopModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface ApiService {
    @GET("bus_stops.json")
    Call<List<BusStopModel>> getBusStopModelList();

    @GET("bus_stops/{id}.json")
    Call<BusStopModel> getBusStopModel(@Path("id") int id);

    @GET("ads.json")
    Call<List<AdModel>> getAdModelList();

    @GET("ads.json")
    Call<List<AdModel>> getAdModelList(@Query("offset") int offset, @Query("limit") int limit);

    @GET("ads.json")
    Call<List<AdModel>> getAdModelListByCode(@Query("code") int code, @Query("offset") int offset, @Query("limit") int limit);

    @GET("ads/{id}.json")
    Call<AdModel> getAdModel(@Path("id") int id);
}
