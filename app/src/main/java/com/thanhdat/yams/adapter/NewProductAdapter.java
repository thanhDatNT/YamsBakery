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
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.List;

public class NewProductAdapter extends BaseAdapter {

    Activity context;
    int new_item;
    List<NewProduct> newProductList;

    public NewProductAdapter(Activity context, int new_item, List<NewProduct> newProductList) {
        this.context = context;
        this.new_item = new_item;
        this.newProductList = newProductList;
    }

    @Override
    public int getCount() {
        return newProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return newProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        FavoriteAdapter.ViewHolder holder;
        if(view == null) {
            //link item view
            holder = new FavoriteAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(new_item, null);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.txtDescription = view.findViewById(R.id.txtDescription);
            holder.txtRating = view.findViewById(R.id.txtRating);


            view.setTag(holder);
        }else {
            holder = (FavoriteAdapter.ViewHolder) view.getTag();
        }
        //Binding data
        NewProduct f = newProductList.get(i);
        holder.imvThumb.setImageResource(f.getThumb());
        holder.txtName.setText(f.getName());
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(f.getPrice())+" đ");
        holder.txtRating.setText(decimalFormat.format(f.getRating()));


        return view;
    }
    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice, txtDescription,txtRating;

    }
}
