package com.mallock.messiiitd.retrofit;

import com.mallock.messiiitd.models.Comment;
import com.mallock.messiiitd.models.Post;

import java.util.HashMap;
import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Mallock on 11/13/2016.
 */

public interface WallService {

    @GET("getPosts.php/")
    Call<List<Post>> getPosts(@Query("userId") String userId);

    @POST("/addPost.php")
    Call<Integer> addPost(@Body Post post);

    @POST("/addComment.php/")
    Call<Integer> addComment(@Body Comment comment);

    @POST("/toggleLike.php")
    Call<Integer> toggleLike(@Body PostUserClass userPostMap);

    @POST("/makeHidden.php")
    Call<Integer> makeHidden(@Body PostUserClass userPostMap);
}
