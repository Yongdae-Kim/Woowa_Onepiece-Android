package com.onepiece.woowahan.issho.presenter;

import android.support.v7.widget.RecyclerView;

import com.onepiece.woowahan.issho.AdListContract;
import com.onepiece.woowahan.issho.model.AdModel;
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
    private int offset = 0;
    private int limit = 10;

    private boolean isRequesting = false;

    public AdListPresenter(AdListContract.View viewListener) {
        this.viewListener = viewListener;
    }

    public void requestAdModelList(final int code) {
        isRequesting = true;
        ApiService service = ApiRequester.getInstacne().getService();
        service.getAdModelListByCode(code, offset, limit).enqueue(new Callback<List<AdModel>>() {
            @Override
            public void onResponse(Response<List<AdModel>> response, Retrofit retrofit) {
                if (!response.isSuccess()) {

                }
                List<AdModel> list = response.body();
                viewListener.displayAdListView(list);
                isRequesting = false;
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public RecyclerView.OnScrollListener scrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            }
        };
    }
}