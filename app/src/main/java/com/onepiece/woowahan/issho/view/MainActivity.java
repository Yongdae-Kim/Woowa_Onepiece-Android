package com.onepiece.woowahan.issho.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.onepiece.woowahan.issho.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.ad, R.id.bus})
    public void btnPressed(TextView tv) {
        switch (tv.getId()) {
            case R.id.ad:
                fragReplace(FragmentFactory.FragmentTag.AD);
                break;
            case R.id.bus:
                fragReplace(FragmentFactory.FragmentTag.BUS_STOP);
                break;
            default:
                break;
        }
    }

    private void fragReplace(FragmentFactory.FragmentTag tag) {
        Fragment newFragment = FragmentFactory.getInstacne().getFragment(tag);
        Bundle args = new Bundle();
        args.putString("title", tag.getTitle());
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.ll_fragment, newFragment);
        transaction.commit();
    }
}

class FragmentFactory {

    private static FragmentFactory factory;
    private static Fragment adFrag;
    private static Fragment busStopFrag;

    private FragmentFactory() {
        adFrag = new AdFragment();
        busStopFrag = new BusStopFragment();
    }

    public static FragmentFactory getInstacne() {
        if (factory == null) {
            factory = new FragmentFactory();
        }
        return factory;
    }

    public Fragment getFragment(FragmentTag tag) {
        switch (tag) {
            case AD:
                return adFrag;
            case BUS_STOP:
                return busStopFrag;
            default:
                return null;
        }
    }

    public enum FragmentTag {
        AD("광고로 찾기"), BUS_STOP("버스정류장으로 찾기");

        private final String title;

        FragmentTag(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
