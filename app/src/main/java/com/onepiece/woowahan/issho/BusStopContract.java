package com.onepiece.woowahan.issho;

import com.onepiece.woowahan.issho.model.BusStopModel;

import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface BusStopContract {

    public interface View {
        void init();

        void setBusStopAutocomplete(List<String> busStopNamelist);


    }

    public interface UserAction {
        void requestBusStopModelList();
    }
}
