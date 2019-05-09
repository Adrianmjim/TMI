package com.example.tmi.view;

import com.example.tmi.model.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected Model model;

    protected CompositeDisposable disposables;

    protected MutableLiveData<Throwable> error = new MutableLiveData<>();

    public LiveData<Throwable> getError() {
        return error;
    }

    public BaseViewModel() {
        model = new Model();
        disposables = new CompositeDisposable();
    }

    @Override protected void onCleared() {
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
        super.onCleared();
    }

}
