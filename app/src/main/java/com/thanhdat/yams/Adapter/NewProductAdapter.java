package com.thanhdat.yams.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder>{
    Context context;
    int layout;
    ArrayList<NewProduct> newProducts;
    OnClickInterface onClickInterface;

    public NewProductAdapter(Context context, int layout, ArrayList<NewProduct> newProduct, OnClickInterface onClickInterface) {
        this.context = context;
        this.newProducts = newProduct;
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
        holder.imvThumb.setImageResource(newProducts.get(position).getThumb());
        holder.tvName.setText(newProducts.get(position).getName());
        holder.tvPrice.setText(newProducts.get(position).getPrice());
        holder.tvRating.setText(newProducts.get(position).getRating());
        holder.tvTag.setText(newProducts.get(position).getTag());
        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.setClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newProducts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView tvName, tvPrice, tvRating, tvTag;
        LinearLayout layoutProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb= itemView.findViewById(R.id.imvThumbHome);
            tvName= itemView.findViewById(R.id.tvProductNameHome);
            tvPrice= itemView.findViewById(R.id.tvPriceHome);
            tvRating= itemView.findViewById(R.id.tvRatingHome);
            tvTag= itemView.findViewById(R.id.tvTagProductHome);
            layoutProduct= itemView.findViewById(R.id.layoutProduct);
        }
    }
}