package com.thanhdat.yams.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.SeeReviewItem;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SeeReviewAdapter extends RecyclerView.Adapter<SeeReviewAdapter.ViewHolder> {

    Context context;
    ArrayList<SeeReviewItem> reviews;

    public SeeReviewAdapter(Context context, ArrayList<SeeReviewItem> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_see_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        byte[] reviewImage = reviews.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(reviewImage, 0, reviewImage.length);
        holder.imvImage.setImageBitmap(bitmap);

        byte[] reviewProfile = reviews.get(position).getProfile();
        Bitmap bitmap1 = BitmapFactory.decodeByteArray(reviewProfile, 0, reviewProfile.length);
        holder.imvProfile.setImageBitmap(bitmap1);

//        Picasso.get().load(reviews.get(position).getProfile()).into(holder.imvProfile);
//        Picasso.get().load(reviews.get(position).getImage()).into(holder.imvImage);

        holder.txtName.setText(reviews.get(position).getName());
        holder.txtContent.setText(reviews.get(position).getContent());
        holder.txtSize.setText(reviews.get(position).getSize());

        holder.rtbRating.setRating((float) reviews.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvProfile, imvImage;
        TextView txtName, txtContent, txtSize;
        RatingBar rtbRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProfile= itemView.findViewById(R.id.imvReviewAva);
            imvImage= itemView.findViewById(R.id.imvReviewImage);

            txtName= itemView.findViewById(R.id.txtNameReview);
            txtContent= itemView.findViewById(R.id.txtReviewText);
            txtSize= itemView.findViewById(R.id.txtSizeReview);

            rtbRating= itemView.findViewById(R.id.rtbSeeReviewItem);
        }
    }
}
