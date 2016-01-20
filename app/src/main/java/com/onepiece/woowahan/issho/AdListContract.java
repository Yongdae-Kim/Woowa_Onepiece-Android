package com.onepiece.woowahan.issho;

import com.onepiece.woowahan.issho.model.AdModel;

import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface AdListContract {

    public interface View {
        void init();
        void displayAdListView(List<AdModel> adModelList);
    }

    public interface UserAction {
        void requestAdModelList(int code);
    }
}
