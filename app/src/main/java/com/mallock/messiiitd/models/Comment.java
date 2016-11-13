package com.mallock.messiiitd.models;

import com.mallock.messiiitd.models.Post;

/**
 * Created by Mallock on 07-10-2016.
 */

public class Comment
{
    int commentId;
    String userId;
    String text;
    String date;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}