package com.example.tmi.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

import com.example.tmi.model.entities.Report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    private final String API_KEY = "j3lna8sn734h46ipmehp5v81ji";

    private final String API_SEC = "1fknfh9cuv4g3dnt8dl940lrib";

    private int i = 0;

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

    public Single<List<Report>> computeVideo(Context context, Uri uri, int step) {
        return extractFrames(context, uri, step)
                       .flatMapObservable(bitmaps -> Observable.fromArray(bitmaps.toArray(new Bitmap[0])))
                       .map(bitmap -> loadPhoto(context, bitmap))
                       .flatMap(this::getPhotoInfo)
                       .toList();
    }
    private Single<List<Bitmap>> extractFrames(Context context, Uri uri, int step) {

        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

        mediaMetadataRetriever.setDataSource(context, uri);
        String METADATA_KEY_DURATION = mediaMetadataRetriever
                                               .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        List<Bitmap> list = new ArrayList<>();

        for(int i = 0; i < Long.parseLong(METADATA_KEY_DURATION); i += step) {
            list.add(mediaMetadataRetriever.getFrameAtTime(i * 1000, MediaMetadataRetriever.OPTION_CLOSEST));
        }

        return Single.just(list);
    }
    private File loadPhoto(Context context, Bitmap bitmap) throws IOException {
        File f = new File(context.getCacheDir(), "cache"+ i++);
        f.createNewFile();

        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();

        return f;

    }
    private Observable<Report> getPhotoInfo(File imageFile) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), imageFile);
        return remoteDataSource.getPhotoInfo(API_KEY, API_SEC, fileBody);
    }



}

