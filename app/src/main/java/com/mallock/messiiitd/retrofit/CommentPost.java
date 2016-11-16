package com.mallock.messiiitd.retrofit;

/**
 * Created by Guroosh Chaudhary on 17-11-2016.
 */

public class CommentPost {
    int postId;
    String userId;
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }


}
