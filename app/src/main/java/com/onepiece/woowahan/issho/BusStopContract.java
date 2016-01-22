package com.onepiece.woowahan.issho;

import android.app.Activity;
import android.widget.AdapterView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.onepiece.woowahan.issho.model.BusStopModel;

import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface BusStopContract {

    interface View {

        void setBusStopAutoComplete(List<String> busStopNamelist);

        void setGoogleMap();

        void showDialogWhenGoogleMapNotSupported(int status);

        void showDialogWhenMarkerClicked(Marker marker);

        void displayBusStopMarkerOnMap(String busStopName);

        void displayAllBusStopMarkerOnMap(List<BusStopModel> busStopModelList);
    }

    interface UserAction {
        void requestBusStopModelList();

        void checkGoogleMapStatus(int status);

        GoogleMap.OnMarkerClickListener markerClickListener();

        AdapterView.OnItemClickListener autoCompleteItemCLickListener();
    }
}
