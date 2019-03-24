package com.example.myapplication.view.main;

import com.example.myapplication.model.entities.Report;
import com.example.myapplication.view.BaseViewModel;

import java.io.File;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<Report> photoLiveData = new MutableLiveData<>();

    public LiveData<Report> getPhoto() {
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
