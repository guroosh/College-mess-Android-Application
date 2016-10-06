package com.mallock.messiiitd.fragments.postWall;

import java.util.ArrayList;

/**
 * Created by Mallock on 07-10-2016.
 */

public class Post {
    String userId;
    boolean isHidden;
    String text;
    ArrayList<Comment> comments;
    String imageUrl;
    int upvotes;
    String dateTime;
}
