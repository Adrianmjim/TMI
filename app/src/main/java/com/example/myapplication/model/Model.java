package com.example.myapplication.model;

import com.github.mhendred.face4j.DefaultFaceClient;
import com.github.mhendred.face4j.FaceClient;
import com.github.mhendred.face4j.exception.FaceClientException;
import com.github.mhendred.face4j.exception.FaceServerException;
import com.github.mhendred.face4j.model.Photo;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    private final String API_KEY = "j3lna8sn734h46ipmehp5v81ji";

    private final String API_SEC = "1fknfh9cuv4g3dnt8dl940lrib";

    private RemoteDataSource remoteDataSource;

    public Model() {
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://api.skybiometry.com/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();

        remoteDataSource = retrofit.create(RemoteDataSource.class);
    }

    public Single<Photo> detect (File imageFile) {
        return remoteDataSource.getPhotoInfo(API_KEY, API_SEC, imageFile);
    }
}
