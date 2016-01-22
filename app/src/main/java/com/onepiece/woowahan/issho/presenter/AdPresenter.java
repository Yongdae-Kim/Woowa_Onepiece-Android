package com.onepiece.woowahan.issho.presenter;

import android.support.design.widget.TabLayout;

import com.onepiece.woowahan.issho.AdContract;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class AdPresenter implements AdContract.UserAction {
    private AdContract.View viewListener;

    public AdPresenter(AdContract.View viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public TabLayout.OnTabSelectedListener adTypeTabSelectedListener() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewListener.displayCurrentTabScreen(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }


}
