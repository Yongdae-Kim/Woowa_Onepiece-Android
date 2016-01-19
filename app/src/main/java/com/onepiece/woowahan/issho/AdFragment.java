package com.onepiece.woowahan.issho;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.*;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class AdFragment extends Fragment {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private String title;

    public AdFragment(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, v);
        init();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void init() {
        System.out.println("init");
        getActivity().setTitle(title);
        initTabLayout();
        initViewPager();
    }

    private void initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(AdType.FOOD.getName()));
        tabLayout.addTab(tabLayout.newTab().setText(AdType.CULTURE.getName()));
        tabLayout.addTab(tabLayout.newTab().setText(AdType.STORE.getName()));
        tabLayout.addTab(tabLayout.newTab().setText(AdType.ETC.getName()));
        tabLayout.setTabGravity(GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(listener);
    }

    private OnTabSelectedListener listener = new OnTabSelectedListener() {
        @Override
        public void onTabSelected(Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(Tab tab) {
        }

        @Override
        public void onTabReselected(Tab tab) {
        }
    };

    private void initViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }

            @Override
            public Fragment getItem(int position) {
                String name = tabLayout.getTabAt(position).getText().toString();
                AdType adType = AdType.get(name);
                return new AdListFragment(adType);
            }
        });
        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
    }

    public enum AdType {
        FOOD("음식", 1), CULTURE("문화", 2), STORE("매장", 3), ETC("기타", 4);

        private final String typeName;
        private final int typeCode;
        private static final Map<String, AdType> lookup = Maps.newHashMap();

        AdType(String typeName, int typeCode) {
            this.typeName = typeName;
            this.typeCode = typeCode;
        }

        static {
            for (AdType v : AdType.values()) {
                lookup.put(v.getName(), v);
            }
        }

        public static AdType get(String name) {
            return lookup.get(name);
        }

        public String getName() {
            return typeName;
        }

        public int getCode() {
            return typeCode;
        }
    }
}

