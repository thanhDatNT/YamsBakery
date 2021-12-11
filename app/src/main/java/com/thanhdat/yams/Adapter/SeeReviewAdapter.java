package com.thanhdat.yams.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        SeeReviewItem review = seeReviewItems.get(position);
        holder.txtReviewText.setText(review.getReviewText());
        holder.txtSizeReview.setText(review.getReviewSize());
        holder.rtbSeeReviewItem.setRating((float) review.getReviewRating());

        byte[] reviewImage = review.getReviewImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(reviewImage, 0, reviewImage.length);
        holder.imvReviewImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return seeReviewItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imvReviewImage;
        TextView txtReviewText, txtSizeReview;
        RatingBar rtbSeeReviewItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //link views
            imvReviewImage = itemView.findViewById(R.id.imvReviewImage);

            txtReviewText = itemView.findViewById(R.id.txtReviewText);
            txtSizeReview = itemView.findViewById(R.id.txtSizeReview);

            rtbSeeReviewItem = itemView.findViewById(R.id.rtbSeeReviewItem);
        }
    }
}
