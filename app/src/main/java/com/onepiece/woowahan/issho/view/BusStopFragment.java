package com.onepiece.woowahan.issho.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
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


    private static final int GOOGLE_MAP_NOT_SUPPORTED_REQUEST_CODE = 10;
    private static final int GOOGLE_MAP_ZOOM = 14;
    private static final int GOOGLE_MAP_BEARING = 90;
    private static final int GOOGLE_MAP_ANIMATION_DURATION = 3000;
    private static final float GOOGLE_MAP_ANCHOR_X = 0.0f;
    private static final float GOOGLE_MAP_ANCHOR_Y = 1.0f;

    private View v;
    private GoogleMap map;

    @Bind(R.id.auto_complete_tv)
    AutoCompleteTextView autoCompleteTv;

    private BusStopPresenter presenter;
    private Map<String, Marker> markerMap = Maps.newHashMap();

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
            final String title = args.getString(MainActivity.ARGUMENT_TITLE);
            getActivity().setTitle(title);
        }
        initGoogleMap();
    }

    @Override
    public void setBusStopAutoComplete(List<String> busStopNamelist) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, busStopNamelist);
        autoCompleteTv.setAdapter(adapter);
        autoCompleteTv.setOnItemClickListener(presenter.autoCompleteItemCLickListener());
    }

    @Override
    public void displayBusStopMarkerOnMap(String busStopName) {
        Marker selectedMarker = markerMap.get(busStopName);
        showDialogWhenMarkerClicked(selectedMarker);
    }

    private void initGoogleMap() {
        int status = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getActivity().getBaseContext());
        presenter.checkGoogleMapStatus(status);
    }

    @Override
    public void setGoogleMap() {
        MapFragment mapFrag = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(googleMap -> {
            googleMap.setMyLocationEnabled(true);
            googleMap.setOnMarkerClickListener(presenter.markerClickListener());
            map = mapFrag.getMap();
        });
    }

    @Override
    public void showDialogWhenGoogleMapNotSupported(int status) {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status,
                getActivity(), GOOGLE_MAP_NOT_SUPPORTED_REQUEST_CODE);
        dialog.show();
    }

    @Override
    public void showDialogWhenMarkerClicked(Marker marker) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(busStop.getName()).append("(진행중인 광고 : ").append(busStop.getAdsCnt()).append(")");
//
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity(), R.style.AlertDialogStyle);
        builder.setTitle(marker.getTitle());
        builder.setMessage(marker.getSnippet());
        builder.setPositiveButton("광고보기", (dialog, which) -> {

        });
        builder.setNegativeButton("취소", null);
        builder.show();
    }

    @Override
    public void displayAllBusStopMarkerOnMap(List<BusStopModel> busStopModelList) {
        if (map != null) {
            map.clear();

            for (BusStopModel busStop : busStopModelList) {
                MarkerOptions markerOptions = generateBusStopMakerOptions(busStop);
                Marker marker = map.addMarker(markerOptions);
                markerMap.put(busStop.getName(), marker);
            }
            String defaultMarkerKey = markerMap.keySet().iterator().next();
            focusToMarker(markerMap.get(defaultMarkerKey));
        }
    }

    private MarkerOptions generateBusStopMakerOptions(BusStopModel busStop) {
        Location loc = busStop.getLoc();
        double lat = Double.parseDouble(loc.getLat());
        double lng = Double.parseDouble(loc.getLng());

        String title = busStop.getName();
        String snippet = loc.getAddr();
        LatLng position = new LatLng(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions()
                .title(title)
                .snippet(snippet)
                .position(position)
                .anchor(GOOGLE_MAP_ANCHOR_X, GOOGLE_MAP_ANCHOR_Y);

        return markerOptions;
    }

    private void focusToMarker(Marker marker) {
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(marker.getPosition())
                .zoom(GOOGLE_MAP_ZOOM)
                .bearing(GOOGLE_MAP_BEARING)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                GOOGLE_MAP_ANIMATION_DURATION, null);
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