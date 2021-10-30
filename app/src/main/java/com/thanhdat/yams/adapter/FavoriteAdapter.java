package com.thanhdat.yams.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.R;

import java.util.List;

public class FavoriteAdapter extends BaseAdapter {

    Activity context;
    int item_listview;
    List<Favorite> favoriteList;

    public FavoriteAdapter(Activity context, int item_listview, List<Favorite> favoriteList) {
        this.context = context;
        this.item_listview = item_listview;
        this.favoriteList = favoriteList;
    }

    @Override
    public int getCount() {
        return favoriteList.size();
    }

    @Override
    public Object getItem(int i) {
        return favoriteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            //link item view
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_listview, null);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.txtOldPrice = view.findViewById(R.id.txtOldPrice);
            holder.txtDescription = view.findViewById(R.id.txtDescription);
            holder.txtRating = view.findViewById(R.id.txtRating);
            holder.txtQuantity = view.findViewById(R.id.txtQuantity);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        //Binding data
        Favorite f = favoriteList.get(i);
        holder.imvThumb.setImageResource(f.getProductThumb());
        holder.txtName.setText(f.getProductName());
        holder.txtPrice.setText(String.valueOf(f.getProductPrice()));
        holder.txtOldPrice.setText(String.valueOf(f.getProductOldPrice()));
        holder.txtRating.setText(String.valueOf(f.getProductRating()));
        holder.txtQuantity.setText(String.valueOf(f.getProductQuantity()));

        return view;

    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice, txtOldPrice, txtDescription,txtRating, txtQuantity;

    }
}
