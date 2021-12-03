<<<<<<< Updated upstream:app/src/main/java/com/thanhdat/yams/Adapter/PostAdapter.java
package com.thanhdat.yams.Adapter;
=======
package com.thanhdat.yams.Adapters;
>>>>>>> Stashed changes:app/src/main/java/com/thanhdat/yams/Adapters/PostAdapter.java

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Activities.FeedCommentActivity;
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

            holder.txtComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FeedCommentActivity.class);
                    context.startActivity(intent);
                }
            });

            holder.imbAddComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FeedCommentActivity.class);
                    context.startActivity(intent);
                }
            });

            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        //Binding data
        Post p = posts.get(i);
        holder.imvThumb.setImageResource(p.getPostThumb());
        holder.txtLike.setText(p.getPostLike());
        holder.txtDescription.setText(p.getPostDescription());
        holder.txtHashtag.setText(p.getPostHashtag());

        return view;
    }
    private static class ViewHolder{
        ImageView imvThumb;
        TextView txtLike, txtDescription, txtHashtag, txtComment;
        ImageButton imbAddComment;

    }
}