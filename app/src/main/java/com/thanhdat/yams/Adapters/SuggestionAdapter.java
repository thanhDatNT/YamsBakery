package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SuggestionAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> products;
    OnClickInterface onClickInterface;

    public SuggestionAdapter(Context context, int layout, ArrayList<Product> newProducts, OnClickInterface clickInterface) {
        this.context = context;
        this.layout = layout;
        this.products = newProducts;
        this.onClickInterface = clickInterface;
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
        ImageView imvThumb, imvLiked, imvNotLike;
        TextView tvName, tvPrice, tvRating, tvTag, tvOldPrice;
        LinearLayout layoutProduct;
        CheckBox chkLike;
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
            holder.tvOldPrice= convertView.findViewById(R.id.tvOldPrice);
            holder.chkLike= convertView.findViewById(R.id.chkLike);
            holder.layoutProduct= convertView.findViewById(R.id.layoutProduct);
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
        if (products.get(position).isFavorite()){
            holder.chkLike.setChecked(true);
            holder.chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    products.get(position).setFavorite(false);
                }
            });
        }
        else{
            holder.chkLike.setChecked(false);
            holder.chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    products.get(position).setFavorite(true);
                }
            });
        }
        if(products.get(position).isPromo()){
            SpannableString spannableString= new SpannableString(String.format("%.0f",products.get(position).getPrice()));
            spannableString.setSpan(new StrikethroughSpan(),0, 5, 0);
            holder.tvOldPrice.setText(spannableString);
            holder.tvOldPrice.setVisibility(View.VISIBLE);
        }
        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(product.getId());
            }
        });
        return convertView;
    }
}
