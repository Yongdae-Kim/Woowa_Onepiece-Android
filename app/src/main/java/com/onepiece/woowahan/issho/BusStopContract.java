package com.onepiece.woowahan.issho;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.onepiece.woowahan.issho.model.BusStopModel;

import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface BusStopContract {

    interface View {

        void setBusStopAutocomplete(List<String> busStopNamelist);

        void setGoogleMap();

        void showDialogWhenGoogleMapNotSupported(int status);

        void showDialogWhenMarkerClicked(Marker marker);

        void displayBusStopMarkerOnMap(List<BusStopModel> busStopModelList);
    }

    interface UserAction {
        void requestBusStopModelList();

        void checkGoogleMapSupported(int status);

        GoogleMap.OnMarkerClickListener markerClickListener();
    }
}
