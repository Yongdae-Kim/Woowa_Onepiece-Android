package com.onepiece.woowahan.issho;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class AdListFragment extends Fragment {

    private View v;
    private AdFragment.AdType adType;

    public AdListFragment(AdFragment.AdType adType) {
        this.adType = adType;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("광고 타입 코드 : " + adType.getCode());
        System.out.println("뷰 생성");
        v = inflater.inflate(R.layout.fragment_ad_list, container, false);
        return v;
    }

}

