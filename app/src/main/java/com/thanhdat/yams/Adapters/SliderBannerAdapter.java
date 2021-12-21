package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SliderBannerAdapter extends SliderViewAdapter<SliderBannerAdapter.SliderVH> {
    ArrayList<Banner> banners;
    Context context;

    public SliderBannerAdapter(ArrayList<Banner> banners, Context context) {
        this.banners = banners;
        this.context = context;
    }

    @Override
    public SliderVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_banner, null);
        return new SliderVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {
        Banner banner= banners.get(position);
        viewHolder.imvBanner.setImageResource(banner.getBanner());
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    class SliderVH extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView imvBanner;

        public SliderVH(View itemView) {
            super(itemView);
            imvBanner= itemView.findViewById(R.id.imvBannerHome);
            this.itemView= itemView;
        }
    }
}
