package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.OrderDetailActivity;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;

import java.util.List;

public class PendingAdapter2 extends RecyclerView.Adapter<PendingAdapter2.PendingViewHolder>{

    List<PendingOrder> pendingOrderList;
    Context context;

    public PendingAdapter2(Context context, List<PendingOrder> pendingOrderList) {
        this.context = context;
        this.pendingOrderList = pendingOrderList;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_order,parent,false);

        return new PendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, int position) {
        PendingOrder pendingOrder = pendingOrderList.get(position);
        Picasso.get().load(pendingOrder.getOrderThumb()).into(holder.imvPendingThumb);
        holder.txtPendingName.setText(pendingOrder.getOrderName());
        holder.txtPendingCode.setText("#" + pendingOrder.getOrderCode());
        holder.txtPendingPrice.setText(String.format("%.0f",pendingOrder.getOrderPrice())+" đ");

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(pendingOrder);
            }
        });

        holder.layoutItemPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(pendingOrder);
            }
        });
    }

    private void goToDetail(PendingOrder pendingOrder) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_pending", pendingOrder);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void release(){
        context = null;
    }
    @Override
    public int getItemCount() {
        return pendingOrderList.size();
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout layoutItemPending;
        ImageView imvPendingThumb;
        TextView txtPendingCode, txtPendingName, txtPendingPrice;
        Button btnDetail;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItemPending = itemView.findViewById(R.id.layoutItemPending);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            imvPendingThumb = itemView.findViewById(R.id.imvPendingThumb);
            txtPendingCode = itemView.findViewById(R.id.txtPendingCode);
            txtPendingName = itemView.findViewById(R.id.txtPendingName);
            txtPendingPrice = itemView.findViewById(R.id.txtPendingPrice);

        }
    }
}
