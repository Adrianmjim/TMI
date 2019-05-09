package com.example.tmi.model;

import com.example.tmi.model.entities.Report;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RemoteDataSource {

    @Multipart
    @POST("fc/faces/detect.json?attributes=all")
    Observable<Report> getPhotoInfo(@Query("api_key") String apiKey, @Query("api_secret") String apiSecret, @Part("file\";filename=\"avatar.jpg\" ") RequestBody file);

}
