package com.thanhdat.yams.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Activities.SeeReviewActivity;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.R;

import java.util.ArrayList;
import java.util.List;

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.PreviousViewHolder> {
    List<PreviousOrder> previousOrderList;
    Context context;

    public PreviousAdapter(Context context,List<PreviousOrder> previousOrderList) {
        this.context = context;
        this.previousOrderList = previousOrderList;
    }

    @NonNull
    @Override
    public PreviousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_previous_order,parent,false);

        return new PreviousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousViewHolder holder, int position) {
        PreviousOrder previousOrder = previousOrderList.get(position);
        holder.imvPreviousThumb.setImageResource(previousOrder.getPreviousThumb());
        holder.txtPreviousName.setText(previousOrder.getPreviousName());
        holder.txtPreviousContent.setText(previousOrder.getPreviousContent());
        holder.txtPreviousQuantity.setText(String.format("X%s",previousOrder.getPreviousQuantity()));
        holder.txtPreviousPrice.setText(String.format("%g",previousOrder.getPreviousPrice())+"đ");

        holder.txtReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSeeReview();
            }
        });

    }

    private void goToSeeReview() {
        Intent intent = new Intent(context, SeeReviewActivity.class);
        context.startActivity(intent);
    }

    public void release(){
        context = null;
    }

    @Override
    public int getItemCount() {
        return previousOrderList.size();
    }

    public class PreviousViewHolder extends RecyclerView.ViewHolder{

        ImageView imvPreviousThumb;
        TextView txtPreviousName, txtPreviousContent, txtPreviousQuantity, txtPreviousPrice, txtReview;
        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            imvPreviousThumb = itemView.findViewById(R.id.imvPreviousThumb);
            txtPreviousName = itemView.findViewById(R.id.txtPreviousName);
            txtPreviousContent = itemView.findViewById(R.id.txtPreviousContent);
            txtPreviousQuantity = itemView.findViewById(R.id.txtPreviousQuantity);
            txtPreviousPrice = itemView.findViewById(R.id.txtPreviousPrice);

            txtReview = itemView.findViewById(R.id.txtReview);
        }
    }
}

