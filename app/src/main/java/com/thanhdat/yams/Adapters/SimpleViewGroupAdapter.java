<<<<<<< Updated upstream:app/src/main/java/com/thanhdat/yams/Adapter/SimpleViewGroupAdapter.java
package com.thanhdat.yams.Adapter;
=======
package com.thanhdat.yams.Adapters;
>>>>>>> Stashed changes:app/src/main/java/com/thanhdat/yams/Adapters/SimpleViewGroupAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SimpleViewGroupAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<SimpleViewGroup> dataList;

    public SimpleViewGroupAdapter(Context context, int layout, ArrayList<SimpleViewGroup> dataList) {
        this.context = context;
        this.layout = layout;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
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
        SimpleViewGroup data= dataList.get(position);
        holder.imvImage.setImageResource(data.getImage());
        holder.tvName.setText(data.getCate());
        return convertView;
    }
}
