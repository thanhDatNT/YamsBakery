<<<<<<< Updated upstream:app/src/main/java/com/thanhdat/yams/Adapter/VoucherAdapter.java
package com.thanhdat.yams.Adapter;
=======
package com.thanhdat.yams.Adapters;
>>>>>>> Stashed changes:app/src/main/java/com/thanhdat/yams/Adapters/VoucherAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.Voucher;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class VoucherAdapter extends BaseAdapter {

    Context context;
    int item_voucher;
    ArrayList<Voucher> vouchers;

    public VoucherAdapter(Context context, int item_voucher, ArrayList<Voucher> vouchers) {
        this.context = context;
        this.item_voucher = item_voucher;
        this.vouchers = vouchers;
    }

    @Override
    public int getCount() {
        return vouchers.size();
    }

    @Override
    public Object getItem(int i) {
        return vouchers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(item_voucher, null);
            holder.imvThumb = view.findViewById(R.id.imvThumb);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtHSD = view.findViewById(R.id.txtHSD);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        //binding data
        Voucher v = vouchers.get(i);
        holder.imvThumb.setImageResource(v.getVoucherThumb());
        holder.txtName.setText(v.getVoucherName());

        holder.txtHSD.setText(v.getVoucherHSD());
        return view;
    }
    private static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtHSD;
    }
}
