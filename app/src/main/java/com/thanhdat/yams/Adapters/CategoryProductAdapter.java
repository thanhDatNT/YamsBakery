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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ViewHolder>{

    Context context;
    ArrayList<Product> products;
    OnClickInterface onClickInterface;

    public CategoryProductAdapter(Context context, int item_favorite, ArrayList<Product> products, OnClickInterface onClickInterface) {
        this.context = context;
        this.products = products;
        this.onClickInterface = onClickInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(products.get(position).getThumbnail()).into(holder.imvThumb);
        holder.tvName.setText(products.get(position).getName());
        holder.tvPrice.setText(String.format("%.0f",products.get(position).getCurrentPrice()));
        holder.tvRating.setText(String.valueOf(products.get(position).getRating()));
        holder.tvQuantity.setText(String.valueOf(products.get(position).getChecked() + "+"));
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
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra(Constant.ID_PRODUCT, products.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView tvName, tvPrice, tvOldPrice, tvRating, tvQuantity;
        LinearLayout layoutProduct;
        CheckBox chkLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb= itemView.findViewById(R.id.imvThumb);
            tvName= itemView.findViewById(R.id.txtName);
            tvPrice= itemView.findViewById(R.id.txtPrice);
            tvOldPrice= itemView.findViewById(R.id.txtOldPrice);
            tvRating= itemView.findViewById(R.id.txtRating);
            tvQuantity= itemView.findViewById(R.id.txtQuantity);
            layoutProduct= itemView.findViewById(R.id.layoutFavourite);
            chkLike = itemView.findViewById(R.id.chkLike);

        }
    }
}
