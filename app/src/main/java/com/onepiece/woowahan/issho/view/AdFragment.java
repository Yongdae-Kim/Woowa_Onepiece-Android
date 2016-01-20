package com.onepiece.woowahan.issho.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Maps;
import com.onepiece.woowahan.issho.AdContract;
import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.presenter.AdPresenter;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.support.design.widget.TabLayout.GRAVITY_FILL;
import static android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class AdFragment extends Fragment implements AdContract.View {

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private AdPresenter presenter;

    public AdFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ad, container, false);
        ButterKnife.bind(this, v);
        presenter = new AdPresenter(this);
        init();


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void init() {
        final String title = getArguments().getString("title");
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
        tabLayout.setOnTabSelectedListener(presenter.adTabSelectedListener(viewPager));
    }

    private void initViewPager() {
        // FragmentStatePagerAdapter 를 사용 할 경우 에러발생
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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

    public enum AdType implements Serializable {
        FOOD("음식", 1), CULTURE("문화", 2), STORE("매장", 3), ETC("기타", 4);

        private final String name;
        private final int code;
        private static final Map<String, AdType> lookup = Maps.newHashMap();

        AdType(String name, int code) {
            this.name = name;
            this.code = code;
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
            return name;
        }

        public int getCode() {
            return code;
        }
    }

}