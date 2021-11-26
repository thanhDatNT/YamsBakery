package com.thanhdat.yams.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PostAdapter;

import java.util.ArrayList;


public class FeedFragment extends Fragment {


    ListView lvFeed;
    ArrayList<Post> posts;
    PostAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);
        lvFeed = view.findViewById(R.id.lvFeed);
        adapter = new PostAdapter(getContext(),R.layout.post_item,initData());
        lvFeed.setAdapter(adapter);
        return view;
    }

    private ArrayList<Post> initData(){
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.img_mango_cake,"123 lượt thích","Bánh xoài kem tươi","#yams #mango"));
        posts.add(new Post(R.drawable.img_bdcake,"123 lượt thích","It's your birthday","#yams #mango"));
        posts.add(new Post(R.drawable.img_mango_cake,"123 lượt thích","Bánh xoài kem tươi","#yams #mango"));
        posts.add(new Post(R.drawable.img_mango_cake,"123 lượt thích","Bánh xoài kem tươi","#yams #mango"));
        return posts;

    }


}