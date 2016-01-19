package com.onepiece.woowahan.issho;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.ll_fragment, newFragment);
        transaction.commit();
    }
}
