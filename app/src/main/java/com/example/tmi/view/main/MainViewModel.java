package com.example.tmi.view.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.example.tmi.model.entities.Mood;
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

    private MutableLiveData<Boolean> load = new MutableLiveData<>();
    private MutableLiveData<List<Report>> sequence = new MutableLiveData<>();
    private MutableLiveData<List<Bitmap>> sequence2 = new MutableLiveData<>();
    private MutableLiveData<List<Mood>> higherMoods = new MutableLiveData<>();
    private MutableLiveData<List<Mood>> coincidencies = new MutableLiveData<>();
    private MutableLiveData<Report> report = new MutableLiveData<>();
    private MutableLiveData<Bitmap> bitmap = new MutableLiveData<>();
    private MutableLiveData<Integer> frame = new MutableLiveData<>();

    public LiveData<Boolean> getLoad() {
        return load;
    }
    public LiveData<Report> getSequence() {
        return report;
    }
    public LiveData<Bitmap> getSequence2() {
        return bitmap;
    }
    public LiveData<List<Mood>> getHigherMoods() {return higherMoods;}
    public LiveData<List<Mood>> getCoincidencys() {return coincidencies;}
    public LiveData<Integer> getFrame() {return frame;}

    private int i = 0;

    private int maxElements = 0;

    private int step;

    public MainViewModel() {
        super();
    }

    void processVideo(Context context, Uri uri) {
        disposables.add(model.extractFrames(context, uri, step * 1000)
                .doOnSubscribe(o -> load.postValue(true))
                .subscribeOn(Schedulers.io())
                .doOnSuccess(this::initSequence2)
                .flatMap(bitmaps -> model.computeVideo(bitmaps, context))
                .doOnSuccess(this::initSequence)
                .flatMap(reports ->
                         model.getHigherMoods(reports)
                            .doOnSuccess(higherMoods::postValue)
                            .ignoreElement().andThen(model.getCoincidencies(reports)))
                .doOnEvent((s,t)-> load.postValue(false))
                .subscribe(coincidencies::postValue, error::postValue));
    }
    private void initSequence(List<Report> reports) {
        sequence.postValue(reports);
        report.postValue(reports.get(0));
        maxElements = reports.size();
    }
    private void initSequence2(List<Bitmap> bitmaps) {
        sequence2.postValue(bitmaps);
        bitmap.postValue(bitmaps.get(0));
    }
    private void initHigherMoods(List<Mood> moods) {
        higherMoods.postValue(moods);
        //higherMood.postValue(moods.get(0));
    }
    private void initCoincidencies(List<Mood> moods) {
        coincidencies.postValue(moods);
        //coincidency.postValue(moods.get(0));
    }
    private void prepareContext(List<Mood> moodList) {
        coincidencies.postValue(moodList);
        maxElements = moodList.size();
    }
    public void next() {
        if (i < maxElements-1) {
            i++;
            showReport();
        }
    }
    public void before() {
        if (i > 0) {
            i--;
            showReport();
        }
    }
    private void showReport() {
        bitmap.postValue(sequence2.getValue().get(i));
        report.postValue(sequence.getValue().get(i));
        frame.postValue(i);
    }

    public int getStep() {
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }
}
