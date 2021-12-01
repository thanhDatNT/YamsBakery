package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhdat.yams.R;

public class FeedCommentActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private CommentAdapter commentAdapter;
//    private List<Comment> commentList;

    EditText addComment;
    ImageView imageProfile;
    TextView post;

    String postId;
    String authorId;

    //FirebaseUser fUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_comment);

        addEventToolbar();

        addComment = findViewById(R.id.txtAddComment);
        imageProfile = findViewById(R.id.imvProfileComment);
        post = findViewById(R.id.btnPostComment);

        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");
        authorId = intent.getStringExtra("authorId");

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addComment.getText().toString().equals("")){
                    Toast.makeText(FeedCommentActivity.this, "You con't send empty comment", Toast.LENGTH_SHORT).show();
                } else {
                    putComment();
                }
            }
        });
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        commentList = new ArrayList<>();
//        commentAdapter = new CommentAdapter(this, commentList, postId);
//
//        recyclerView.setAdapter(commentAdapter);
//
//
//        fUser = FirebaseAuth.getInstance().getCurrentUser();

        getUserImage();



        getComment();
    }

    private void addEventToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarComment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_back_pink);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getComment() {

//        FirebaseDatabase.getInstance().getReference().child("Comments").child(postId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                commentList.clear();
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Comment comment = snapshot.getValue(Comment.class);
//                    commentList.add(comment);
//                }
//
//                commentAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private void putComment() {

//        HashMap<String, Object> map = new HashMap<>();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Comments").child(postId);
//
//        String id = ref.push().getKey();
//
//        map.put("id", id);
//        map.put("comment", addComment.getText().toString());
//        map.put("publisher", fUser.getUid());
//
//        addComment.setText("");
//
//        ref.child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(CommentActivity.this, "Comment added!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(CommentActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    private void getUserImage() {

//        FirebaseDatabase.getInstance().getReference().child("Users").child(fUser.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                if (user.getImageurl().equals("default")) {
//                    imageProfile.setImageResource(R.mipmap.ic_launcher);
//                } else {
//                    Picasso.get().load(user.getImageurl()).into(imageProfile);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
    }

}