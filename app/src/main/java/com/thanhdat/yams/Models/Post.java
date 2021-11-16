package com.thanhdat.yams.Models;

public class Post {

    private int postThumb;
    private String postLike;
    private String postDescription;
    private String postHashtag;

    public Post(int postThumb, String postLike, String postDescription, String postHashtag) {
        this.postThumb = postThumb;
        this.postLike = postLike;
        this.postDescription = postDescription;
        this.postHashtag = postHashtag;
    }

    public int getPostThumb() {
        return postThumb;
    }

    public void setPostThumb(int postThumb) {
        this.postThumb = postThumb;
    }

    public String getPostLike() {
        return postLike;
    }

    public void setPostLike(String postLike) {
        this.postLike = postLike;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getPostHashtag() {
        return postHashtag;
    }

    public void setPostHashtag(String postHashtag) {
        this.postHashtag = postHashtag;
    }
}