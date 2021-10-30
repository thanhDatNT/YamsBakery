package com.thanhdat.yams.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class SliderBannerAdapter extends SliderViewAdapter<SliderBannerAdapter.SliderVH> {
    ArrayList<Banner> banners;
    Context context;
    LinearLayout.LayoutParams params;

    public SliderBannerAdapter(ArrayList<Banner> banners, Context context) {
        this.banners = banners;
        this.context = context;
    }

    @Override
    public SliderVH onCreateViewHolder(ViewGroup parent) {
        params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        View inflate = LayoutInflater.from(parent.getContext()).inflate((XmlPullParser) linearLayout, null);
        return null;
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {

    }

    @Override
    public int getCount() {
        return banners.size();
    }

    class SliderVH extends SliderViewAdapter.ViewHolder{
        View itemView;

        public SliderVH(View itemView) {
            super(itemView);
        }
    }
}
