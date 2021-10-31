package com.thanhdat.yams.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, int layout, ArrayList<Category> categories) {
        this.context = context;
        this.layout = layout;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView tvName;
        ImageView imvImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(layout,null);
            holder= new ViewHolder();
            holder.imvImage= convertView.findViewById(R.id.imgCategory);
            holder.tvName= convertView.findViewById(R.id.tvCategory);
            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }
        Category category= categories.get(position);
        holder.imvImage.setImageResource(category.getImage());
        holder.tvName.setText(category.getCate());
        return convertView;
    }
}
