<<<<<<< Updated upstream:app/src/main/java/com/thanhdat/yams/Adapter/PaymentProductAdapter.java
package com.thanhdat.yams.Adapter;
=======
package com.thanhdat.yams.Adapters;
>>>>>>> Stashed changes:app/src/main/java/com/thanhdat/yams/Adapters/PaymentProductAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.thanhdat.yams.Models.PaymentProduct;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class PaymentProductAdapter extends RecyclerView.Adapter<PaymentProductAdapter.ViewHolder> {

    Context context;
    int layout;
    ArrayList<PaymentProduct> paymentProducts;

    public PaymentProductAdapter(Context context, int layout, ArrayList<PaymentProduct> paymentProducts) {
        this.context = context;
        this.layout = layout;
        this.paymentProducts = paymentProducts;
    }
    @NonNull
    @Override
    public PaymentProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentProductAdapter.ViewHolder holder, int position) {
        holder.imvThumb.setImageResource(paymentProducts.get(position).getProductThumb());
        holder.txtName.setText(paymentProducts.get(position).getProductName());
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(paymentProducts.get(position).getProductPrice())+"đ");
        holder.txtDescription.setText(paymentProducts.get(position).getProductDescription());
        holder.txtQuantity.setText( "Số lượng: " + decimalFormat.format(paymentProducts.get(position).getProductQuantity()));
    }

    @Override
    public int getItemCount() {
        return paymentProducts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice, txtDescription, txtQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Link view
            imvThumb = itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}

