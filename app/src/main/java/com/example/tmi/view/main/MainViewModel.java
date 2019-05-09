package com.example.tmi.view.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.tmi.model.entities.Report;
import com.example.tmi.view.BaseViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<Report> photoLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> load = new MutableLiveData<>();
    private MutableLiveData<List<Report>> sequence = new MutableLiveData<>();

    public LiveData<Report> getPhoto() {
        return photoLiveData;
    }
    public LiveData<Boolean> getLoad() {
        return load;
    }
    public LiveData<List<Report>> getSequence() {
        return sequence;
    }

    public MainViewModel() {
        super();
    }

    void processVideo(Context context, Uri uri, int step) {
        disposables.add(model.computeVideo(context, uri, step)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(o -> load.postValue(true))
                .doOnEvent((s,t)-> load.postValue(false))
                .subscribe(reports -> sequence.postValue(reports), throwable -> error.postValue(throwable)));
    }
}
