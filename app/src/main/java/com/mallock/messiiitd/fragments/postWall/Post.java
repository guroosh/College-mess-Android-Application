package com.mallock.messiiitd.fragments.postWall;

import java.util.ArrayList;

/**
 * Created by Mallock on 07-10-2016.
 */

// TODO: 13-10-2016 add PostId, List of users who have liked post OR boolean, whether requesting user has liked post.
public class Post {
    private String userId;
    private boolean isHidden;
    private String text;
    private ArrayList<Comment> comments;
    private String imageUrl, userImageUrl;
    private int upvotes;
    private String dateTime;

    public String getUserId() {
        return userId;
    }

    public boolean isHidden() {
        return isHidden;
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

    public int getUpvotes() {
        return upvotes;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
}
