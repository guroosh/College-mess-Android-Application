package com.mallock.messiiitd;

import android.content.Context;

import com.mallock.messiiitd.fragments.menu.MenuItem;
import com.mallock.messiiitd.models.Comment;
import com.mallock.messiiitd.models.Post;

import java.util.ArrayList;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Mallock on 13-10-2016.
 */

public class DataSupplier {

    public static String getUserId() {
        return "akshat140091";
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static String getBaseURL() {
        return "http://192.168.55.70/mess/";
    }

    public static String getImageURL() {
        return "http://192.168.55.70/mess/messMenu.png";
    }
}
