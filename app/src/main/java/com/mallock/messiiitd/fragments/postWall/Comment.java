package com.mallock.messiiitd.fragments.postWall;

/**
 * Created by Mallock on 07-10-2016.
 */

public class Comment {
    Post post;
    String userId;
    String text;
    String dateTime;

    public void setPost(Post post)
    {
        this.post = post;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setUserId(String id)
    {
        this.userId = id;
    }

    public void setDateTime(String date)
    {
        this.dateTime = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }
}
