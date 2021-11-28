package com.thanhdat.yams.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.Models.PaymentProduct;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentAdapter extends BaseAdapter {

    Activity context;
    int item_payment;
    ArrayList<PaymentProduct> paymentProducts;

    public PaymentAdapter(Activity context, int item_payment, ArrayList<PaymentProduct> paymentProducts) {
        this.context = context;
        this.item_payment = item_payment;
        this.paymentProducts = paymentProducts;
    }

    public PaymentAdapter(Context applicationContext, ArrayList<PaymentProduct> paymentProducts) {

    }

    @Override
    public int getCount() {
        return paymentProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return paymentProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            //link item view
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_payment
                    , null);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.txtDescription = view.findViewById(R.id.txtDescription);
            holder.txtQuantity = view.findViewById(R.id.txtQuantity);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //Binding data
        PaymentProduct p = paymentProducts.get(i);
        holder.imvThumb.setImageResource(p.getProductThumb());
        holder.txtName.setText(p.getProductName());
        holder.txtDescription.setText(p.getProductDescription());
        holder.txtPrice.setText(String.format("%g",p.getProductPrice()) +"đ");
        holder.txtQuantity.setText(String.format("%g",p.getProductQuantity()));

        return view;
    }

    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice, txtDescription, txtQuantity;
    }
}
