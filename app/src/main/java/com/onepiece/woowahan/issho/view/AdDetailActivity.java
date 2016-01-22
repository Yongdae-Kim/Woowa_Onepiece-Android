package com.onepiece.woowahan.issho.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.onepiece.woowahan.issho.AdDetailContract;
import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.presenter.AdDetailPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 21..
 */
public class AdDetailActivity extends AppCompatActivity implements AdDetailContract.View {

    private AdDetailPresenter presenter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_detail);
        ButterKnife.bind(this);

        presenter = new AdDetailPresenter(this);
        initView();
    }

    private void initView() {
        initToolbar();

        Intent args = getIntent();
        if (args != null) {
            AdModel adModel = (AdModel) args.getSerializableExtra("adModel");
//            getActivity().setTitle(title);
        }
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }
}