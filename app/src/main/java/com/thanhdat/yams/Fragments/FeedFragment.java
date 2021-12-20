package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhdat.yams.Activities.CartActivity;

import com.thanhdat.yams.Activities.ChatActivity;
import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.PostAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FeedFragment extends Fragment {
    boolean isLoaded= false;
    Toolbar toolbarFeed;
    ProgressBar progressBar;
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
        progressBar= view.findViewById(R.id.progressBarFeed);
        posts= new ArrayList<>();

//        Get feed data from API
        new readFeedDataAPI().execute("https://thanhdatnt.github.io/database/feeds.json");
        navigate();
        setUpListView();
        return view;
    }

    private void setUpListView() {
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLoaded){
                    progressBar.setVisibility(View.GONE);
                    adapter = new PostAdapter(getContext(), R.layout.item_post, posts);
                    lvFeed.setAdapter(adapter);
                }
                else {
                    setUpListView();
                }
            }
        }, 2000);
    }

    private void navigate() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarFeed);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setTitle(null);
            }
        }
        toolbarFeed.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.mnuCartFeed){
                    startActivity(new Intent(getContext(), CartActivity.class));
                }
                if(item.getItemId() == R.id.mnuChatFeed){
                    startActivity(new Intent(getContext(), ChatActivity.class));
                }
                return false;
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.feed_heading, menu);
    }

    private class readFeedDataAPI extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url= new URL(strings[0]);
                InputStreamReader streamReader= new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader reader= new BufferedReader(streamReader);
                String line="";
                while((line=reader.readLine()) != null){
                    stringBuilder.append(line);
                }
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object= new JSONObject(s);
                JSONArray array= object.getJSONArray("feeds");
                ArrayList<String> hashtags= new ArrayList<>();
                for(int i=0; i<array.length(); i++){
                    JSONObject feed= array.getJSONObject(i);
                    Post post= new Post();
                    post.setContent(feed.getString("content"));
                    post.setDate(feed.getString("date"));
                    post.setId(feed.getInt("id"));
                    post.setLiked(feed.getInt("liked"));
                    post.setPhoto(feed.getString("image"));
                    JSONArray tags= feed.getJSONArray("hashtags");
                    for(int j=0; j<tags.length(); j++){
                        hashtags.add(tags.getString(j));
                    }
                    post.setTags(hashtags);
                    posts.add(post);
                }
                isLoaded= true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}