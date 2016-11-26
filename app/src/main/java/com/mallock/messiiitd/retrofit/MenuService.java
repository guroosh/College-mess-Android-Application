package com.mallock.messiiitd.retrofit;

import com.mallock.messiiitd.models.Menu;
import com.mallock.messiiitd.models.Post;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Guroosh Chaudhary on 17-11-2016.
 */

public interface MenuService
{
    @GET("getMenu.php/")
    Call<Menu> getMenu();

    @POST("menuUpVote.php/")
    Call<Integer> menuUpVote(@Body UpVotePost upVotePost);

    @POST("menuDownVote.php/")
    Call<Integer> menuDownVote(@Body DownVotePost downVotePost);
}
