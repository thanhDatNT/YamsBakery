package com.thanhdat.yams.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Diet;
import com.thanhdat.yams.R;

import java.util.List;

public class DietAdapter extends BaseAdapter {
    Activity context;
    int items_listview;
    List<Diet>dietList;

    public DietAdapter(Activity context, int items_listview, List<Diet> dietList) {
        this.context = context;
        this.items_listview = items_listview;
        this.dietList = dietList;
    }

    @Override
    public int getCount() {
        return dietList.size();
    }

    @Override
    public Object getItem(int i) {
        return dietList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(items_listview,null);
            holder.imvThumb= view.findViewById(R.id.imvThumb);

            holder.txtName=view.findViewById(R.id.txtName);
            holder.txtPrice=view.findViewById(R.id.txtPrice);
            holder.txtContent=view.findViewById(R.id.txtContent);
            holder.txtRating=view.findViewById(R.id.txtRate);
            holder.txtQuantity=view.findViewById(R.id.txtQuantity);

            view.setTag(holder);

        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        Diet diet =dietList.get(i);
        holder.imvThumb.setImageResource(diet.getDietThumb());
        holder.txtName.setText(diet.getDietName());
        holder.txtPrice.setText(diet.getDietPrice());
        holder.txtContent.setText(diet.getDietContent());
        holder.txtRating.setText(String.valueOf(diet.getDietRating()));
        holder.txtQuantity.setText(String.valueOf(diet.getDietQuantity())+ "+");
        return view;
    }
    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtPrice,txtContent,txtRating,txtQuantity;

    }
}
