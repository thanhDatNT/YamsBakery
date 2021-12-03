package com.thanhdat.yams.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class PendingAdapter extends BaseAdapter {
    OnClickInterface clickInterface;
    Context context;
    int pending_order_item;
    ArrayList<PendingOrder> pendingOrders;

    public PendingAdapter(Context context, int pending_order_item, ArrayList<PendingOrder> pendingOrders, OnClickInterface clickInterface) {
        this.context = context;
        this.pending_order_item = pending_order_item;
        this.pendingOrders = pendingOrders;
        this.clickInterface= clickInterface;
    }

    @Override
    public int getCount() {
        return pendingOrders.size();
    }

    @Override
    public Object getItem(int i) {
        return pendingOrders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view ==null){
            holder = new ViewHolder();
            view = inflater.inflate(pending_order_item,null);
            holder.imvThumb = view.findViewById(R.id.imvPendingThumb);
            holder.txtCode = view.findViewById(R.id.txtPendingCode);
            holder.txtName = view.findViewById(R.id.txtPendingName);
            holder.txtPrice = view.findViewById(R.id.txtPendingPrice);
            holder.layout= view.findViewById(R.id.pendingOrderLayout);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        //Binding Data
        PendingOrder pendingOrder = pendingOrders.get(i);
        holder.imvThumb.setImageResource(pendingOrder.getOrderThumb());
        holder.txtName.setText(pendingOrder.getOrderName());
        holder.txtPrice.setText(String.format("%g",pendingOrder.getOrderPrice())+"đ");
        holder.txtCode.setText(pendingOrder.getOrderCode());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clickInterface.setClick(0);
            }
        });
        return view;
    }

    class ViewHolder{
        ImageView imvThumb;
        TextView txtCode, txtName, txtPrice;
        LinearLayout layout;
    }
}
