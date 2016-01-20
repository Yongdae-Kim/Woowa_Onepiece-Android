package com.onepiece.woowahan.issho.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.onepiece.woowahan.issho.BusStopContract;
import com.onepiece.woowahan.issho.presenter.BusStopPresenter;
import com.onepiece.woowahan.issho.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 19..
 */

public class BusStopFragment extends Fragment implements BusStopContract.View {

    @Bind(R.id.auto_complete_tv)
    AutoCompleteTextView autoCompleteTv;

    private BusStopPresenter presenter;
    public BusStopFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bus_stop, container, false);
        ButterKnife.bind(this, v);
        presenter = new BusStopPresenter(this);
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
        String[] autoCompleteArr = presenter.getBusStopAutoCompleteArr();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, autoCompleteArr);
        autoCompleteTv.setAdapter(adapter);
    }
}