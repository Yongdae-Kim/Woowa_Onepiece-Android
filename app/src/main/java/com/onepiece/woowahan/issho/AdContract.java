package com.onepiece.woowahan.issho;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

/**
 * Created by useruser on 2016. 1. 20..
 */
public interface AdContract {
    public interface View {
        void init();
    }

    public interface UserAction {
        TabLayout.OnTabSelectedListener adTabSelectedListener(ViewPager viewPager);
    }
}