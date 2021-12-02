package com.thanhdat.yams.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.SeeReviewItem;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SeeReviewAdapter extends RecyclerView.Adapter<SeeReviewAdapter.ViewHolder> {

    Context context;
    ArrayList<SeeReviewItem> seeReviewItems;

    public SeeReviewAdapter(Context context, ArrayList<SeeReviewItem> seeReviewItems) {
        this.context = context;
        this.seeReviewItems = seeReviewItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View customView = inflater.inflate(R.layout.item_see_review,parent,false);

        return new ViewHolder(customView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imvAvaReview.setImageResource(seeReviewItems.get(position).getReviewAva());
        holder.imvProductReview.setImageResource(seeReviewItems.get(position).getReviewProductThumb());

        holder.txtNameReview.setText(seeReviewItems.get(position).getReviewName());
        holder.txtReviewText.setText(seeReviewItems.get(position).getReviewText());
        holder.txtSizeReview.setText(seeReviewItems.get(position).getReviewSize());
        holder.txtToppingReview.setText(seeReviewItems.get(position).getReviewTopping());

        holder.rtbSeeReviewItem.setRating((float) seeReviewItems.get(position).getReviewRating());

    }

    @Override
    public int getItemCount() {
        return seeReviewItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imvAvaReview, imvProductReview;
        TextView txtNameReview, txtReviewText, txtSizeReview, txtToppingReview;
        RatingBar rtbSeeReviewItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //link views
            imvAvaReview = itemView.findViewById(R.id.imvAvaReview);
            imvProductReview = itemView.findViewById(R.id.imvProductReview);

            txtNameReview = itemView.findViewById(R.id.txtNameReview);
            txtReviewText = itemView.findViewById(R.id.txtReviewText);
            txtSizeReview = itemView.findViewById(R.id.txtSizeReview);
            txtToppingReview = itemView.findViewById(R.id.txtToppingReview);

            rtbSeeReviewItem = itemView.findViewById(R.id.rtbSeeReviewItem);
        }
    }
}
