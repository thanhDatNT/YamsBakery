package com.thanhdat.yams.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
        TextView txtLike, txtDescription, txtHashtag;
    }
}
