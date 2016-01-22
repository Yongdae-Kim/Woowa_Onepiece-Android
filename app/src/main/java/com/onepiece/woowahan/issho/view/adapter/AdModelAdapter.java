package com.onepiece.woowahan.issho.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onepiece.woowahan.issho.R;
import com.onepiece.woowahan.issho.model.AdModel;
import com.onepiece.woowahan.issho.model.Image;
import com.onepiece.woowahan.issho.view.AdDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by useruser on 2016. 1. 20..
 */
public class AdModelAdapter extends RecyclerView.Adapter<AdModelAdapter.ViewHolder> {

    private static final String EXTRA_AD_MODEL = "adModel";
    private static final String NO_IMG_URL = "http://www.woowahan.com/wp-content/uploads/2012/01/main_logo.gif";

    private Activity activity;
    private List<AdModel> adModelList;

    public AdModelAdapter(Activity activity, List<AdModel> adModelList) {
        this.activity = activity;
        this.adModelList = adModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AdModel adModel = adModelList.get(position);
        loadAdThumbnail(holder, adModel);
        holder.adTv.setText(adModel.getTitle());
        holder.adCv.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AdDetailActivity.class);
            if (intent.getSerializableExtra("EXTRA_AD_MODEL") != null) {
                intent.putExtra(EXTRA_AD_MODEL, adModel);
            }
            activity.startActivity(intent);
        });
    }

    private void loadAdThumbnail(final ViewHolder holder, AdModel adModel) {
        String imgUri = getAdThumbnailImgUri(adModel);
        holder.adIv.setTag(imgUri);
        if (imgUri.equals(holder.adIv.getTag())) {
            Picasso.with(activity)
                    .load(imgUri)
                    .into(holder.adIv);
        }
    }

    private String getAdThumbnailImgUri(AdModel adModel) {
        List<Image> images = adModel.getImages();
        String imgUri = images.size() != 0 ? images.get(0).getUri() : NO_IMG_URL;
        return imgUri;
    }

    @Override
    public int getItemCount() {
        return this.adModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ad_cv)
        CardView adCv;
        @Bind(R.id.ad_iv)
        ImageView adIv;
        @Bind(R.id.ad_tv)
        TextView adTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}