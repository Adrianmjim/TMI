package com.example.myapplication.view.main;

import com.example.myapplication.view.BaseViewModel;
import com.github.mhendred.face4j.model.Photo;

import java.io.File;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<Photo> photoLiveData = new MutableLiveData<>();

    public LiveData<Photo> getPhoto() {
        return photoLiveData;
    }

    public MainViewModel() {
        super();
    }

    public void detect(File image) {
        disposables.add(model.detect(image)
                .subscribeOn(Schedulers.io())
                .subscribe(photoLiveData::postValue, error::postValue));
    }
}
