package com.mallock.messiiitd.retrofit;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import okhttp3.MultipartBody;

/**
 * Created by Mallock on 11/17/2016.
 */

public interface FileUploadService {
    @Multipart
    @POST("upload")
    Call<String> upload(@Part("file") MultipartBody.Part file);
}
