package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class PreviousAdapter extends BaseAdapter {
    Context context;
    int previous_order_item;
    ArrayList<PreviousOrder> previousOrders;

    public PreviousAdapter(Context context, int previous_order_item, ArrayList<PreviousOrder> previousOrders) {
        this.context = context;
        this.previous_order_item = previous_order_item;
        this.previousOrders = previousOrders;
    }

    @Override
    public int getCount() {
        return previousOrders.size();
    }

    @Override
    public Object getItem(int i) {
        return previousOrders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PreviousAdapter.ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view ==null){
            holder = new PreviousAdapter.ViewHolder();
            view = inflater.inflate(previous_order_item,null);
            holder.imvThumb = view.findViewById(R.id.imvPreviousThumb);
            holder.txtContent = view.findViewById(R.id.txtPreviousContent);
            holder.txtName = view.findViewById(R.id.txtPreviousName);
            holder.txtPrice = view.findViewById(R.id.txtPreviousPrice);
            view.setTag(holder);

        }else{
            holder = (PreviousAdapter.ViewHolder) view.getTag();
        }

        //Binding Data
        PreviousOrder previousOrder = previousOrders.get(i);
        holder.imvThumb.setImageResource(previousOrder.getPreviousThumb());
        holder.txtName.setText(previousOrder.getPreviousName());
        holder.txtPrice.setText(String.format("%g",previousOrder.getPreviousPrice())+"đ");
        holder.txtContent.setText(previousOrder.getPreviousContent());

        return view;
    }
    private static class ViewHolder{
        ImageView imvThumb;
        TextView txtContent, txtName, txtPrice;

    }
}

