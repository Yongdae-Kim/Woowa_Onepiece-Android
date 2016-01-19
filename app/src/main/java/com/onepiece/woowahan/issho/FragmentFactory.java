package com.onepiece.woowahan.issho;

import android.support.v4.app.Fragment;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class FragmentFactory {

    private static FragmentFactory factory;
    private static Fragment adFrag;
    private static Fragment busStopFrag;

    private FragmentFactory() {
        adFrag = new AdFragment(FragmentTag.AD.getTitle());
        busStopFrag = new BusStopFragment(FragmentTag.BUS_STOP.getTitle());
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
        AD("광고로 찾기"), BUS_STOP("버스정류장으 찾기");

        private final String title;

        FragmentTag(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
