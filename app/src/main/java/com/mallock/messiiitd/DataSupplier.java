package com.mallock.messiiitd;

import android.content.Context;

import com.mallock.messiiitd.fragments.menu.WeeklyMenu;
import com.mallock.messiiitd.fragments.postWall.Post;

import java.util.ArrayList;

/**
 * Created by Mallock on 13-10-2016.
 */

public class DataSupplier {

    //todo return list of all posts from server, or hardcode                @Guroosh
    public static ArrayList<Post> getPosts(Context context) {
        Post post1 = new Post();
        post1.setDateTime("19:40, 16th October, 2016");
        post1.setUserId("akshat14009");
        post1.setImageUrl("http://www.pngall.com/wp-content/uploads/2016/03/Food-Free-Download-PNG.png");
        post1.setText(context.getString(R.string.test_string_long));
        post1.setUpvotes(6);
        ArrayList<Post> list = new ArrayList<>();
        list.add(post1);

        Post p2 = new Post();
        p2.setDateTime("19:40, 16th October, 2016");
        p2.setUserId("akshat14009");
        p2.setText(context.getString(R.string.test_string_long));
        p2.setUpvotes(6);
        list.add(p2);
        return list;
    }

    //todo return list of one Weekly Menu from server, or hardcode          @Paurush
    public static WeeklyMenu getMenu(Context context) {
        return null;
    }
}
