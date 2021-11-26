package com.thanhdat.yams.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.Models.SettingMyProfile;
import com.thanhdat.yams.R;

import java.util.ArrayList;
import java.util.List;

public class SettingMyProfileAdapter extends BaseAdapter {
    Context context;
    int item_layout;
    List<SettingMyProfile> settingMyProfileList;

    public SettingMyProfileAdapter(Context context, int item_layout, List<SettingMyProfile> settingMyProfileList) {
        this.context = context;
        this.item_layout = item_layout;
        this.settingMyProfileList = settingMyProfileList;
    }

    @Override
    public int getCount() {
        return settingMyProfileList.size();
    }

    @Override
    public Object getItem(int i) {
        return settingMyProfileList.get(i);
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
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout,null);
            holder.txtNameInfor = view.findViewById(R.id.txtNameInfor);
            holder.txtInfor=view.findViewById(R.id.txtInfor);
            holder.imvEdit=view.findViewById(R.id.imvEdit);
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        SettingMyProfile myProfile =settingMyProfileList.get(i);
        holder.txtNameInfor.setText(myProfile.getProfileName());
        holder.txtInfor.setText(myProfile.getProfileInformation());
        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //edit data
            }
        });

        return view;
    }
    private class ViewHolder{
        TextView txtNameInfor, txtInfor;
        ImageView imvEdit;
    }
}
