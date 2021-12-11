package com.thanhdat.yams.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SuggestionAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> products;

    public SuggestionAdapter(Context context, int layout, ArrayList<Product> newProducts) {
        this.context = context;
        this.layout = layout;
        this.products = newProducts;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
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
        Product product= products.get(position);
        Picasso.get().load(product.getThumbnail()).into(holder.imvThumb);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.format("%.0f",product.getPrice()));
        holder.tvRating.setText(String.valueOf(product.getRating()));
        holder.tvTag.setText(product.getTag());
        return convertView;
    }
}
