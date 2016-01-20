package com.onepiece.woowahan.issho.presenter;

import android.app.Activity;

import com.onepiece.woowahan.issho.BusStopContract;
import com.onepiece.woowahan.issho.model.BusStopModel;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class BusStopPresenter implements BusStopContract.UserAction {

    private BusStopContract.View viewListener;

    public BusStopPresenter(BusStopContract.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public String[] getBusStopAutoCompleteArr() {
        String[] arr = new String[8];
        arr[0] = "용대";
        arr[1] = "용대1";
        arr[2] = "태준";
        arr[3] = "태준1";
        arr[4] = "태양";
        arr[5] = "태양1";
        arr[6] = "여은";
        arr[7] = "여은1";
        return arr;
    }
}
