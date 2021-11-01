package com.thanhdat.yams.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SuggestionAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<NewProduct> newProducts;

    public SuggestionAdapter(Context context, int layout, ArrayList<NewProduct> newProducts) {
        this.context = context;
        this.layout = layout;
        this.newProducts = newProducts;
    }

    @Override
    public int getCount() {
        return newProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return newProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        ImageView imvThumb;
        TextView tvName, tvPrice, tvRating, tvTag;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout,null);
            holder= new ViewHolder();
            holder.imvThumb= convertView.findViewById(R.id.imvThumbHome);
            holder.tvName= convertView.findViewById(R.id.tvProductNameHome);
            holder.tvPrice= convertView.findViewById(R.id.tvPriceHome);
            holder.tvRating= convertView.findViewById(R.id.tvRatingHome);
            holder.tvTag= convertView.findViewById(R.id.tvTagProductHome);
            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        NewProduct newProduct= newProducts.get(position);
        holder.imvThumb.setImageResource(newProduct.getThumb());
        holder.tvName.setText(newProduct.getName());
        holder.tvPrice.setText(newProduct.getPrice());
        holder.tvRating.setText(newProduct.getRating());
        holder.tvTag.setText(newProduct.getTag());
        return convertView;
    }
}
