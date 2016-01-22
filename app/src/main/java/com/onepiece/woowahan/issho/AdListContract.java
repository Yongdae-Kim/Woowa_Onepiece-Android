package com.onepiece.woowahan.issho;

import android.support.v7.widget.RecyclerView;

import com.onepiece.woowahan.issho.model.AdModel;

import java.util.List;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface AdListContract {

    interface View {

        void displayAdListView(List<AdModel> adModelList);
    }

    interface UserAction {
        void requestAdModelList(int code);

        RecyclerView.OnScrollListener scrollListener();
    }
}
