package com.thanhdat.yams.Models;

public class Post {
    private int postLogo;
    private int postThumb;
    private int postMoreIcon;
    private int postLikeIcon;
    private int postCommentIcon;
    private String postUser;
    private String postAddress;
    private String postLike;
    private String postPublisher;
    private String postDescription;

    private String postSeeComment;

    public Post(int postLogo, int postThumb, int postMoreIcon, int postLikeIcon, int postCommentIcon, String postUser, String postAddress, String postLike, String postPublisher, String postDescription,  String postSeeComment) {
        this.postLogo = postLogo;
        this.postThumb = postThumb;
        this.postMoreIcon = postMoreIcon;
        this.postLikeIcon = postLikeIcon;
        this.postCommentIcon = postCommentIcon;
        this.postUser = postUser;
        this.postAddress = postAddress;
        this.postLike = postLike;
        this.postPublisher = postPublisher;
        this.postDescription = postDescription;

        this.postSeeComment = postSeeComment;
    }

    public Post() {
    }

    public int getPostLogo() {
        return postLogo;
    }

    public void setPostLogo(int postLogo) {
        this.postLogo = postLogo;
    }

    public int getPostThumb() {
        return postThumb;
    }

    public void setPostThumb(int postThumb) {
        this.postThumb = postThumb;
    }

    public int getPostMoreIcon() {
        return postMoreIcon;
    }

    public void setPostMoreIcon(int postMoreIcon) {
        this.postMoreIcon = postMoreIcon;
    }

    public int getPostLikeIcon() {
        return postLikeIcon;
    }

    public void setPostLikeIcon(int postLikeIcon) {
        this.postLikeIcon = postLikeIcon;
    }

    public int getPostCommentIcon() {
        return postCommentIcon;
    }

    public void setPostCommentIcon(int postCommentIcon) {
        this.postCommentIcon = postCommentIcon;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public String getPostLike() {
        return postLike;
    }

    public void setPostLike(String postLike) {
        this.postLike = postLike;
    }

    public String getPostPublisher() {
        return postPublisher;
    }

    public void setPostPublisher(String postPublisher) {
        this.postPublisher = postPublisher;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }


    public String getPostSeeComment() {
        return postSeeComment;
    }

    public void setPostSeeComment(String postSeeComment) {
        this.postSeeComment = postSeeComment;
    }
}
