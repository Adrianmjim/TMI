package com.example.myapplication.model;

import com.example.myapplication.model.entities.Report;
import com.github.mhendred.face4j.DefaultFaceClient;
import com.github.mhendred.face4j.FaceClient;
import com.github.mhendred.face4j.exception.FaceClientException;
import com.github.mhendred.face4j.exception.FaceServerException;
import com.github.mhendred.face4j.model.Photo;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    private final String API_KEY = "j3lna8sn734h46ipmehp5v81ji";

    private final String API_SEC = "1fknfh9cuv4g3dnt8dl940lrib";

    private RemoteDataSource remoteDataSource;

    public Model() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://api.skybiometry.com/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .client(client)
                                    .build();

        remoteDataSource = retrofit.create(RemoteDataSource.class);
    }

    public Single<Report> detect (File imageFile) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"),imageFile);
        return remoteDataSource.getPhotoInfo(API_KEY, API_SEC, fileBody);
    }
}
