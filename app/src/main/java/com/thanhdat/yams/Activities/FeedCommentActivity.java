package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;

public class FeedCommentActivity extends AppCompatActivity {

    EditText edtAddComment;
    ImageView imvProfile;
    Button btnPost;
    TextView txtPublisher, txtDescription, txtHashtag;

    String postId;
    String authorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_comment);

        addEventToolbar();

        edtAddComment = findViewById(R.id.edtAddComment);
        imvProfile = findViewById(R.id.imvProfileComment);
        btnPost = findViewById(R.id.btnPostComment);
        txtDescription = findViewById(R.id.txtDescription);
        txtHashtag = findViewById(R.id.txtHashtag);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Post post = (Post) bundle.get("object_post");

        txtDescription.setText(post.getContent());
        String tags="";
        for(int j=0; j<post.getTags().size(); j++){
            tags += "#"+post.getTags().get(j);
        }
        txtHashtag.setText(tags);

        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");
        authorId = intent.getStringExtra("authorId");

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtAddComment.getText().toString().equals("")){
                    Toast.makeText(FeedCommentActivity.this, "You can't send empty comment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addEventToolbar() {
        Toolbar toolbarComment = findViewById(R.id.toolbarComment);
        setSupportActionBar(toolbarComment);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarComment.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }
}