package com.example.myapplication.model;

import com.example.myapplication.model.entities.Report;


import io.reactivex.Single;
import okhttp3.MultipartBody;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteDataSource {

    @Multipart
    @POST("fc/faces/detect.json?attributes=all")
    Single<Report> getPhotoInfo(@Query("api_key") String apiKey, @Query("api_secret") String apiSecret, @Part("file\";filename=\"avatar.jpg\" ") RequestBody file);

}
