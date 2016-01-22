package com.onepiece.woowahan.issho.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onepiece.woowahan.issho.AdListContract;
import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.presenter.AdListPresenter;
import com.onepiece.woowahan.issho.view.adapter.AdModelAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 19..
 */

public class AdListFragment extends Fragment implements AdListContract.View {

    private AdListPresenter presenter;

    private View v;
    private AdFragment.AdType adType;

    @Bind(R.id.ad_recycler_view)
    RecyclerView recyclerView;

    private AdModelAdapter adModelAdapter;

    public AdListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ad_list, container, false);
        ButterKnife.bind(this, v);

        presenter = new AdListPresenter(this);
        initView();

        return v;
    }

    private void initView() {
        initRecyclerView();

        Bundle args = getArguments();
        if (args != null) {
            adType = (AdFragment.AdType) getArguments().getSerializable(AdFragment.ARGUMENT_AD_TYPE);
            presenter.requestAdModelList(adType.getCode());
        }

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(presenter.scrollListener());
    }

    @Override
    public void displayAdListView(List<AdModel> adModelList) {
        adModelAdapter = new AdModelAdapter(this.getActivity(), adModelList);
        recyclerView.setAdapter(adModelAdapter);
    }
}

