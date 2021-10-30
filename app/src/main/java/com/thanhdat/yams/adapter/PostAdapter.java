package com.thanhdat.yams.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context fContext;
    ArrayList<Post> fPost;

    public PostAdapter(Context fContext, List<Post> fPost) {
        this.fContext = fContext;
        this.fPost = (ArrayList<Post>) fPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View customView = inflater.inflate(R.layout.post_item,parent,false);

        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imvLogo.setImageResource(fPost.get(position).getPostLogo());
        holder.imvThumb.setImageResource(fPost.get(position).getPostThumb());
        holder.imvMore.setImageResource(fPost.get(position).getPostMoreIcon());
        holder.imvLike.setImageResource(fPost.get(position).getPostLikeIcon());
        holder.imvComment.setImageResource(fPost.get(position).getPostCommentIcon());
        holder.txtUser.setText(fPost.get(position).getPostUser());
        holder.txtAddress.setText(fPost.get(position).getPostAddress());
        holder.txtLike.setText(fPost.get(position).getPostLike());
        holder.txtPublisher.setText(fPost.get(position).getPostPublisher());
        holder.txtDescription.setText(fPost.get(position).getPostDescription());
        holder.txtSeeComment.setText(fPost.get(position).getPostSeeComment());

    }

    @Override
    public int getItemCount() {
        return fPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imvLogo, imvThumb, imvMore, imvLike, imvComment;
        public TextView txtUser, txtAddress, txtLike, txtPublisher, txtDescription, txtHashtag, txtSeeComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvLogo = itemView.findViewById(R.id.imvLogo);
            imvThumb = itemView.findViewById(R.id.imvThumb);
            imvMore = itemView.findViewById(R.id.imvMore);
            imvLike = itemView.findViewById(R.id.imvLike);
            imvComment = itemView.findViewById(R.id.imvComment);
            txtUser = itemView.findViewById(R.id.txtUser);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtLike = itemView.findViewById(R.id.txtLike);
            txtPublisher = itemView.findViewById(R.id.txtPublisher);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            txtSeeComment = itemView.findViewById(R.id.txtSeeComment);
        }
    }
}
