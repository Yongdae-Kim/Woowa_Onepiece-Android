package com.onepiece.woowahan.issho.presenter;

import android.view.View;
import android.widget.AdapterView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.common.collect.Lists;
import com.onepiece.woowahan.issho.BusStopContract;
import com.onepiece.woowahan.issho.model.BusStopModel;
import com.onepiece.woowahan.issho.network.ApiRequester;
import com.onepiece.woowahan.issho.network.ApiService;

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
        ApiService service = ApiRequester.getInstacne().getService();
        service.getBusStopModelList().enqueue(new Callback<List<BusStopModel>>() {
            @Override
            public void onResponse(Response<List<BusStopModel>> response, Retrofit retrofit) {
                if (!response.isSuccess()) {
                }
                List<BusStopModel> list = response.body();
                List<String> busStopNameList = Lists.transform(list, busStopModel -> busStopModel.getName());
                viewListener.setBusStopAutoComplete(busStopNameList);
                viewListener.displayAllBusStopMarkerOnMap(list);
                isRequesting = false;
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });
    }


    @Override
    public AdapterView.OnItemClickListener autoCompleteItemCLickListener() {
        return (parent, view, position, id) -> {
            String busStopName = parent.getItemAtPosition(position).toString();
            viewListener.displayBusStopMarkerOnMap(busStopName);
        };
    }

    @Override
    public void checkGoogleMapStatus(int status) {
        if (status != ConnectionResult.SUCCESS) {
            viewListener.showDialogWhenGoogleMapNotSupported(status);
        } else {
            viewListener.setGoogleMap();
        }
    }

    @Override
    public GoogleMap.OnMarkerClickListener markerClickListener() {
        return marker -> {
            viewListener.showDialogWhenMarkerClicked(marker);
            return false;
        };
    }
}
