package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import com.thanhdat.yams.Activities.FeedCommentActivity;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    Context context;
    int post_item;
    ArrayList<Post> posts;


    public PostAdapter(Context context, int post_item, ArrayList<Post> posts) {
        this.context = context;
        this.post_item = post_item;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null){
            holder = new ViewHolder();
            view = inflater.inflate(post_item,null);
            holder.imvThumb = view.findViewById(R.id.imvPostThumb);
            holder.txtLike = view.findViewById(R.id.txtLike);
            holder.txtDescription = view.findViewById(R.id.txtDescription);
            holder.txtHashtag = view.findViewById(R.id.txtHashtag);
            holder.txtComment = view.findViewById(R.id.txtSeeComment);
            holder.imbAddComment = view.findViewById(R.id.imbAddComment);
            holder.txtDate = view.findViewById(R.id.tvDate);
            holder.chkFeedLike = view.findViewById(R.id.chkFeedLike);



            holder.txtComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post item_post = posts.get(i);
                Intent intent = new Intent(context, FeedCommentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_post",item_post);
                intent.putExtras(bundle);
                context.startActivity(intent);
                }
            });

            holder.imbAddComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post item_post = posts.get(i);
                    Intent intent = new Intent(context, FeedCommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("object_post",item_post);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        //Binding data
        Post p = posts.get(i);
        Picasso.get().load(p.getPhoto()).into(holder.imvThumb);
        holder.txtLike.setText(String.valueOf(p.getLiked()));
        int likes = Integer.parseInt(String.valueOf(p.getLiked()));
        holder.chkFeedLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(compoundButton.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(compoundButton.getContext(), "Unliked", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.txtDescription.setText(p.getContent());
        holder.txtDate.setText(p.getDate());
        String tags="";
        for(int j=0; j<p.getTags().size(); j++){
            tags += "#"+p.getTags().get(j);
        }
        holder.txtHashtag.setText(tags);

        return view;
    }


    private static class ViewHolder{
        ImageView imvThumb;
        TextView txtLike, txtDescription, txtHashtag, txtComment, txtDate;
        ImageButton imbAddComment;
        CheckBox chkFeedLike;
    }
}