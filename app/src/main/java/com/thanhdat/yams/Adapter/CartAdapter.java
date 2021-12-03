package com.thanhdat.yams.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.List;

public class CartAdapter extends BaseAdapter {
    Activity context;
    int items_listview;
    List<Cart> cartList;

    public CartAdapter(Activity context, int items_listview, List<Cart> cartList) {
        this.context = context;
        this.items_listview = items_listview;
        this.cartList = cartList;
    }


    public void AddListItemAdapter(List<Cart> itemPlus)
    {
        //add list  to current array list of data
        cartList.addAll(itemPlus);
        //notify UI
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(items_listview,null);
            holder.imvThumb=view.findViewById(R.id.imvThumb);
            holder.txtName=view.findViewById(R.id.txtName);
            holder.txtSize=view.findViewById(R.id.txtSize);
            holder.txtPrice= view.findViewById(R.id.txtPrice);
            holder.txtNumber=view.findViewById(R.id.txtNumber);
            holder.txtRemain=view.findViewById(R.id.txtRemain);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        Cart cart = cartList.get(i);
        holder.imvThumb.setImageResource(cart.getCartThumb());
        holder.txtName.setText(cart.getCartName());
        holder.txtSize.setText(cart.getCartSize());
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(cart.getCartPrice())+"đ");
        holder.txtNumber.setText(String.valueOf(cart.getCartNumber()));
        holder.txtRemain.setText(String.valueOf(cart.getCartRemain()));

        return view;
    }
    public static  class ViewHolder{
        ImageView imvThumb;
        TextView txtName,txtSize, txtPrice,txtNumber,txtRemain;

    }
}
