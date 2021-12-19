package com.thanhdat.yams.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<Product> products;
    OnClickInterface onClickInterface;

    public ProductAdapter(Context context, int layout, ArrayList<Product> product, OnClickInterface onClickInterface) {
        this.context = context;
        this.products = product;
        this.layout= layout;
        this.onClickInterface= onClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(products.get(position).getThumbnail()).into(holder.imvThumb);
        holder.tvName.setText(products.get(position).getName());
        holder.tvPrice.setText(String.format("%.0f",products.get(position).getCurrentPrice()));
        holder.tvRating.setText(String.valueOf(products.get(position).getRating()));
        holder.tvTag.setText(products.get(position).getTag());
        if (products.get(position).isFavorite()){
            holder.imvLiked.setVisibility(View.VISIBLE);
            holder.imvNotLiked.setVisibility(View.GONE);
        }
        else{
            holder.imvNotLiked.setVisibility(View.VISIBLE);
            holder.imvLiked.setVisibility(View.GONE);
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
                onClickInterface.setClick(products.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb, imvLiked, imvNotLiked;
        TextView tvName, tvPrice, tvRating, tvTag, tvOldPrice;
        LinearLayout layoutProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb= itemView.findViewById(R.id.imvThumbHome);
            imvLiked= itemView.findViewById(R.id.imvAddedFavoriteHome);
            imvNotLiked= itemView.findViewById(R.id.imvAddFavoriteHome);
            tvName= itemView.findViewById(R.id.tvProductNameHome);
            tvPrice= itemView.findViewById(R.id.tvPriceHome);
            tvRating= itemView.findViewById(R.id.tvRatingHome);
            tvTag= itemView.findViewById(R.id.tvTagProductHome);
            tvOldPrice= itemView.findViewById(R.id.tvOldPrice);
            layoutProduct= itemView.findViewById(R.id.layoutProduct);


        }
    }
}