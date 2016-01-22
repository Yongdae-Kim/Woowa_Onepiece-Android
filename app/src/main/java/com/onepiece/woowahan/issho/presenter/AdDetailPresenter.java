package com.onepiece.woowahan.issho.presenter;

import com.onepiece.woowahan.issho.AdDetailContract;
import com.onepiece.woowahan.issho.view.AdDetailActivity;

/**
 * Created by useruser on 2016. 1. 21..
 */
public class AdDetailPresenter implements AdDetailContract.UserAction {

    private AdDetailContract.View viewListener;

    public AdDetailPresenter(AdDetailContract.View viewListener) {
        this.viewListener = viewListener;
    }
}
