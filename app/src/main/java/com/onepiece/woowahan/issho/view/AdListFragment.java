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

    private View v;
    private AdFragment.AdType adType;
    private AdListPresenter presenter;


    @Bind(R.id.ad_recycler_view)
    RecyclerView recyclerView;

    private AdModelAdapter adModelAdapter;

    public AdListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ad_list, container, false);
        ButterKnife.bind(this, v);
        init();

        presenter = new AdListPresenter(this);

        Bundle args = getArguments();
        if (args != null) {
            adType = (AdFragment.AdType) getArguments().getSerializable("adType");
            System.out.println(adType.getCode());
            presenter.requestAdModelList(adType.getCode());
        }
        return v;
    }


    @Override
    public void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void displayAdListView(List<AdModel> adModelList) {
        adModelAdapter = new AdModelAdapter(this.getActivity(), adModelList);
        recyclerView.setAdapter(adModelAdapter);
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


        }
    };

}

