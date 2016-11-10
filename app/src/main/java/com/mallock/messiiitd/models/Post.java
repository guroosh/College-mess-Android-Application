package com.mallock.messiiitd.models;

import java.util.ArrayList;

/**
 * Created by Mallock on 07-10-2016.
 */

// TODO: 13-10-2016 add PostId, List of users who have liked post OR boolean, whether requesting user has liked post.
public class Post {
    private String userId;
    private boolean hidden;
    private String text;
    private boolean liked;
    private ArrayList<Comment> comments;
    private String imageUrl, userImageUrl;
    private int upVotes;
    private String date;

    public String getUserId() {
        return userId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public String getDate() {
        return date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
