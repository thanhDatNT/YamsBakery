package com.thanhdat.yams.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {
    Context context;
    ArrayList<Product>diets;
    OnClickInterface onClickInterface;

    public DietAdapter(Context context, ArrayList<Product> diets, OnClickInterface onClickInterface) {
        this.context = context;
        this.diets = diets;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_diet_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(diets.get(position).getThumbnail()).into(holder.imvThumb);
        holder.txtName.setText(diets.get(position).getName());
        holder.txtPrice.setText(String.format("%.0f",diets.get(position).getCurrentPrice())+"đ");
        holder.txtOldPrice.setText(String.format("%.0f",diets.get(position).getPrice())+"đ");
        if (diets.get(position).isFavorite()){
            holder.chkLike.setChecked(true);
            holder.chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    diets.get(position).setFavorite(false);
                }
            });
        }
        else{
            holder.chkLike.setChecked(false);
            holder.chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    diets.get(position).setFavorite(true);
                }
            });
        }
        if(diets.get(position).isPromo()){
            SpannableString spannableString= new SpannableString(String.format("%.0f",diets.get(position).getPrice()));
            spannableString.setSpan(new StrikethroughSpan(),0, 5, 0);
            holder.txtOldPrice.setText(spannableString);
            holder.txtOldPrice.setVisibility(View.VISIBLE);
        }
        holder.txtContent.setText(diets.get(position).getDescription());
        holder.txtRate.setText(String.valueOf(diets.get(position).getRating()));
        holder.txtQuantity.setText(String.valueOf(diets.get(position).getChecked())+"+");
        holder.layoutProductDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onClickInterface.setClick(diets.get(position).getId());
//                Product item= diets.get(position);
//                Intent intent = new Intent(context, ProductDetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("productItem",item);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra(Constant.ID_PRODUCT, diets.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return diets.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvThumb;
        TextView txtName, txtPrice, txtOldPrice,txtContent,txtRate,txtQuantity;
        CardView layoutProductDiet;
        CheckBox chkLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtOldPrice = itemView.findViewById(R.id.txtOldPrice);
            txtContent=itemView.findViewById(R.id.txtContent);
            txtRate = itemView.findViewById(R.id.txtRate);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            layoutProductDiet =itemView.findViewById(R.id.layoutProductDiet);
            chkLike = itemView.findViewById(R.id.chkLike);

        }
    }
}
