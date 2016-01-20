package com.onepiece.woowahan.issho;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface BusStopContract {

    public interface View {
        void init();
    }

    public interface UserAction {
        String[] getBusStopAutoCompleteArr();
    }
}
