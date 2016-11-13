package com.mallock.messiiitd;

import android.content.Context;

import com.mallock.messiiitd.fragments.menu.MenuItem;
import com.mallock.messiiitd.fragments.menu.WeeklyMenu;
import com.mallock.messiiitd.models.Comment;
import com.mallock.messiiitd.models.Post;

import java.util.ArrayList;

/**
 * Created by Mallock on 13-10-2016.
 */

public class DataSupplier {

    //todo return list of all posts from server, or hardcode                @Guroosh
    /*public static ArrayList<Post> getPosts(Context context) {
        Post post1 = new Post();
        post1.setDate("19:40, 16th October, 2016");
        post1.setUserId("akshat14009");
        post1.setImageUrl("http://www.pngall.com/wp-content/uploads/2016/03/Food-Free-Download-PNG.png");
        post1.setText(context.getString(R.string.test_string_long));
        post1.setUpVotes(6);
        ArrayList<Post> list = new ArrayList<>();
        list.add(post1);

        Post p2 = new Post();
        p2.setDate("19:40, 16th October, 2016");
        p2.setUserId("akshat14009");
        p2.setText(context.getString(R.string.test_string_long));
        p2.setUpVotes(6);
        list.add(p2);
        return list;
    }*/

    /*public static ArrayList<Comment> getComments(Context context)
    {
        ArrayList<Comment> list = new ArrayList<>();
        Comment comment1 = new Comment();
//        comment1.setPost(post);
        comment1.setText("this is a comment");
        comment1.setDateTime("19:40, 16th October, 2016");
        comment1.setUserId("akshat14009");
        list.add(comment1);
        return list;
    }*/

    //todo return list of one Weekly Menu from server, or hardcode          @Paurush
    public static WeeklyMenu getMenu(Context context) {
        return null;
    }

    public static ArrayList<MenuItem> getMenuForDay(int position) {
        ArrayList<MenuItem> list = new ArrayList<>();
        if (position == 0) {
            MenuItem item1 = new MenuItem();
            item1.setItemName("Bread");
            list.add(item1);
            MenuItem i1 = new MenuItem();
            i1.setItemName("Omelet");
            list.add(i1);
            MenuItem q = new MenuItem();
            q.setItemName("Butter");
            list.add(q);
        } else if (position == 1) {

            MenuItem item1 = new MenuItem();
            item1.setItemName("Rajma");
            list.add(item1);
            MenuItem i1 = new MenuItem();
            i1.setItemName("Rice");
            list.add(i1);
            MenuItem q = new MenuItem();
            q.setItemName("Raita");
            list.add(q);
        }else if (position == 2) {

            MenuItem item1 = new MenuItem();
            item1.setItemName("Bread Pakora");
            list.add(item1);
            MenuItem i1 = new MenuItem();
            i1.setItemName("Tea");
            list.add(i1);
        }else if (position == 3) {
            MenuItem item1 = new MenuItem();
            item1.setItemName("Roti");
            list.add(item1);
            MenuItem i1 = new MenuItem();
            i1.setItemName("Chicken");
            list.add(i1);
            MenuItem q = new MenuItem();
            q.setItemName("Paneer");
            list.add(q);
            MenuItem q1 = new MenuItem();
            q1.setItemName("Salad");
            list.add(q1);
        }

        return list;
    }

    public static String getUserId(){
        return "akshat14009";
    }
}
