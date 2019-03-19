package com.example.myapplication.model;

import com.github.mhendred.face4j.model.Photo;

import java.io.File;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RemoteDataSource {

    @Multipart
    @POST("fc/faces/detect.json?attributes=all")
    Single<Photo> getPhotoInfo(@Query("api_key") String apiKey, @Query("api_secret") String apiSecret, @Body File photo);

}
