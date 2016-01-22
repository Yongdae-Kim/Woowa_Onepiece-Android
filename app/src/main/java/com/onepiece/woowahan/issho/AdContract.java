package com.onepiece.woowahan.issho;

import android.support.design.widget.TabLayout;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface AdContract {
    interface View {
        void displayCurrentTabScreen(TabLayout.Tab tab);
    }

    interface UserAction {
        TabLayout.OnTabSelectedListener adTypeTabSelectedListener();
    }
}