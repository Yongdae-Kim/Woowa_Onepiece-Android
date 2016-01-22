package com.onepiece.woowahan.issho.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.onepiece.woowahan.issho.AdContract;
import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.presenter.AdPresenter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.GRAVITY_FILL;
import static android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class AdFragment extends Fragment implements AdContract.View {

    private AdPresenter presenter;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    public AdFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, v);

        presenter = new AdPresenter(this);
        initView();

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView() {
        Bundle args = getArguments();
        if (args != null) {
            final String title = args.getString("title");
            getActivity().setTitle(title);
        }
        initTabLayout();
        initViewPager();
    }

    private void initTabLayout() {
        List<AdType> adTypeList = AdType.getAdTypeList();
        for (AdType adType : adTypeList) {
            tabLayout.addTab(tabLayout.newTab().setText(adType.getName()));
        }
        tabLayout.setTabGravity(GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(presenter.adTypeTabSelectedListener());
    }

    private void initViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }

            @Override
            public Fragment getItem(int position) {
                String name = tabLayout.getTabAt(position).getText().toString();
                AdType adType = AdType.get(name);
                Fragment adListFrag = new AdListFragment();
                Bundle args = new Bundle();
                args.putSerializable("adType", adType);
                adListFrag.setArguments(args);
                return adListFrag;
            }
        });
        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void displayCurrentTabScreen(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    public enum AdType implements Serializable {
        FOOD("음식", 1), CULTURE("문화", 2), STORE("매장", 3), ETC("기타", 4);

        private final String name;
        private final int code;
        private static Map<String, AdType> adTypeMap;
        private static List<AdType> adTypeList;

        AdType(String name, int code) {
            this.name = name;
            this.code = code;
        }

        static {
            adTypeMap = Maps.newHashMap();
            for (AdType v : AdType.values()) {
                adTypeMap.put(v.getName(), v);
            }
            adTypeList = Lists.newArrayList(adTypeMap.values());
            Collections.sort(adTypeList, Ordering.natural().nullsFirst());
        }


        public static AdType get(String name) {
            return adTypeMap.get(name);
        }

        public static List<AdType> getAdTypeList() {
            return adTypeList;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }
    }

}