package com.onepiece.woowahan.issho.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.common.collect.Maps;
import com.onepiece.woowahan.issho.BusStopContract;
import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.model.BusStopModel;
import com.onepiece.woowahan.issho.model.Location;
import com.onepiece.woowahan.issho.presenter.BusStopPresenter;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 19..
 */

public class BusStopFragment extends Fragment implements BusStopContract.View {

    @Bind(R.id.auto_complete_tv)
    AutoCompleteTextView autoCompleteTv;

    private BusStopPresenter presenter;

    private Map<String, Marker> markerMap = Maps.newHashMap();

    private View v;
    private GoogleMap googleMap;

    public BusStopFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            v = inflater.inflate(R.layout.fragment_bus_stop, container, false);
        } catch (InflateException e) {

        }

        ButterKnife.bind(this, v);

        presenter = new BusStopPresenter(this);
        initView();
        presenter.requestBusStopModelList();


        return v;
    }

    private void initView() {
        Bundle args = getArguments();
        if (args != null) {
            final String title = args.getString("title");
            getActivity().setTitle(title);
        }
        usingGoogleMap();
    }

    private void usingGoogleMap() {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity().getBaseContext());
        presenter.checkGoogleMapSupported(status);
    }

    @Override
    public void setGoogleMap() {
        googleMap = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(presenter.markerClickListener());
    }

    @Override
    public void showDialogWhenGoogleMapNotSupported(int status) {
        int requestCode = 10;
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
                getActivity(), requestCode);
        dialog.show();
    }

    @Override
    public void setBusStopAutocomplete(List<String> busStopNamelist) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, busStopNamelist);
        autoCompleteTv.setAdapter(adapter);
    }

    @Override
    public void showDialogWhenMarkerClicked(Marker marker) {
        marker.showInfoWindow();
        googleMap
                .moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
//        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void displayBusStopMarkerOnMap(List<BusStopModel> busStopModelList) {
        if (googleMap != null) {
            googleMap.clear();

            for (BusStopModel busStop : busStopModelList) {
                MarkerOptions markerOptions = generateMakerOptions(busStop);
                Marker marker = googleMap.addMarker(markerOptions);
                markerMap.put(busStop.getName(), marker);
            }
        }
        Marker defaultMarker = markerMap.get("한라병원");
        this.showDialogWhenMarkerClicked(defaultMarker);
    }

    private MarkerOptions generateMakerOptions(BusStopModel busStop) {
        MarkerOptions markerOptions = new MarkerOptions();

        Location loc = busStop.getLoc();
        double lat = Double.parseDouble(loc.getLat());
        double lng = Double.parseDouble(loc.getLng());

        String title = busStop.getName();
        String snippet = loc.getAddr();
        LatLng position = new LatLng(lat, lng);

        markerOptions.title(title);
        markerOptions.snippet(snippet);
        markerOptions.position(position);

        return markerOptions;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
        }
        ButterKnife.unbind(this);
    }
}