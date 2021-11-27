package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.NotificationActivity;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PostAdapter;

import java.util.ArrayList;


public class FeedFragment extends Fragment {

    Toolbar toolbarFeed;

    ListView lvFeed;
    ArrayList<Post> posts;
    PostAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);
        //link Views
        toolbarFeed = view.findViewById(R.id.toolbarFeed);

        lvFeed = view.findViewById(R.id.lvFeed);
        adapter = new PostAdapter(getContext(),R.layout.post_item,initData());
        lvFeed.setAdapter(adapter);

        ((MainActivity)getActivity()).setSupportActionBar(toolbarFeed);
        setHasOptionsMenu(true);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(null);

        goToCartOrNotification();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.feed_heading,menu);
    }

    private void goToCartOrNotification() {
        toolbarFeed.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.mnuNotificationFeed){
                    startActivity(new Intent(getContext(), NotificationActivity.class));
                }
                if(item.getItemId() == R.id.mnuCartFeed){
                    startActivity(new Intent(getContext(), CartActivity.class));
                }
                return false;
            }
        });

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