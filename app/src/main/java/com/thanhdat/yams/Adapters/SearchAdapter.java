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
import android.widget.Filter;
import android.widget.Filterable;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<Product> products, productList;
    SelectedProduct selectedProduct;

    public SearchAdapter(Context context, int item_favorite, ArrayList<Product> products, SelectedProduct selectedProduct) {
        this.context = context;
        this.products = products;
        this.selectedProduct = selectedProduct;
        productList = new ArrayList<>(products);
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
        holder.tvPrice.setText(String.format("%.0f",products.get(position).getCurrentPrice()) + "đ");
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedProduct.selectedProduct(products.get(getAdapterPosition()));
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }
    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Product> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(productList);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Product p : productList){
                    if(p.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(p);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            products.clear();
            products.addAll((Collection<? extends Product>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface SelectedProduct{
        void selectedProduct(Product product);
    }
}