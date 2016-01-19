package com.onepiece.woowahan.issho;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 19..
 */
public class BusStopFragment extends Fragment {

    @Bind(R.id.auto_complete_tv)
    AutoCompleteTextView autoCompleteTv;

    private String title;

    public BusStopFragment(String title) {
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bus_stop, container, false);
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
        getActivity().setTitle(title);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, getArr());
        autoCompleteTv.setAdapter(adapter);
    }

    private String[] getArr() {
        String[] arr = new String[8];
        arr[0] = "용대";
        arr[1] = "용대1";
        arr[2] = "태준";
        arr[3] = "태준1";
        arr[4] = "태양";
        arr[5] = "태양1";
        arr[6] = "여은";
        arr[7] = "여은1";
        return arr;
    }
}