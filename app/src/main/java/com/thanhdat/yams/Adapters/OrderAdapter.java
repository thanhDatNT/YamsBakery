package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    int layout;
    ArrayList<Cart> paymentProducts;

    public OrderAdapter(Context context, int layout, ArrayList<Cart> paymentProducts) {
        this.context = context;
        this.layout = layout;
        this.paymentProducts = paymentProducts;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Picasso.get().load(paymentProducts.get(position).getThumb()).into(holder.imvThumb);
        holder.txtName.setText(paymentProducts.get(position).getProductName() + "("+ paymentProducts.get(position).getProductSize() + ")");
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(paymentProducts.get(position).getPrice())+"đ");
        holder.txtTopping.setText("Topping: " + paymentProducts.get(position).getTopping());
        holder.txtQuantity.setText("Số lượng: " + paymentProducts.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return paymentProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice, txtTopping, txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Link view
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtTopping = itemView.findViewById(R.id.txtTopping);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}

