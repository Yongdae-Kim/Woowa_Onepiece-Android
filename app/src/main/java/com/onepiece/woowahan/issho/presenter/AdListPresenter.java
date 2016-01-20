package com.onepiece.woowahan.issho.presenter;

import android.app.AlertDialog;

import com.onepiece.woowahan.issho.AdListContract;
import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.network.ApiCallback;
import com.onepiece.woowahan.issho.network.ApiRequester;
import com.onepiece.woowahan.issho.network.ApiService;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class AdListPresenter implements AdListContract.UserAction {

    private AdListContract.View viewListener;

    public AdListPresenter(AdListContract.View viewListener) {
        this.viewListener = viewListener;
    }

    public void requestAdModelList(final int code) {
        ApiRequester.getInstacne().call(new ApiCallback() {
            @Override
            public void callbackMethod(ApiService service) {
                service.getAdModelListByCode(code).enqueue(new Callback<List<AdModel>>() {
                    // 로그 추가 할 것
                    @Override
                    public void onResponse(Response<List<AdModel>> response, Retrofit retrofit) {
                        if (!response.isSuccess()) {
                        }

                        List<AdModel> list = response.body();
                        viewListener.displayAdListView(list);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
        });
    }
}
