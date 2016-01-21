package com.onepiece.woowahan.issho.presenter;

import com.google.common.collect.Lists;
import com.onepiece.woowahan.issho.BusStopContract;
import com.onepiece.woowahan.issho.model.BusStopModel;
import com.onepiece.woowahan.issho.network.ApiRequester;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class BusStopPresenter implements BusStopContract.UserAction {

    private BusStopContract.View viewListener;

    private boolean isRequesting = false;

    public BusStopPresenter(BusStopContract.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void requestBusStopModelList() {
        isRequesting = true;
        ApiRequester.getInstacne().call(service -> service.getBusStopModelList().enqueue(new Callback<List<BusStopModel>>() {
            @Override
            public void onResponse(Response<List<BusStopModel>> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                }
                List<BusStopModel> list = response.body();
                List<String> busStopNameList = Lists.transform(list, busStopModel -> busStopModel.getName());
                viewListener.setBusStopAutocomplete(busStopNameList);
                isRequesting = false;
            }

            @Override
            public void onFailure(Throwable t) {
            }
        }));
    }
}
