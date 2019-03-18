package com.example.myapplication.model;

import com.github.mhendred.face4j.DefaultFaceClient;
import com.github.mhendred.face4j.FaceClient;
import com.github.mhendred.face4j.exception.FaceClientException;
import com.github.mhendred.face4j.exception.FaceServerException;
import com.github.mhendred.face4j.model.Photo;

import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Single;

public class Model {

    private final String API_KEY = "j3lna8sn734h46ipmehp5v81ji";

    private final String API_SEC = "1fknfh9cuv4g3dnt8dl940lrib";

    private FaceClient client;

    public Model() {
        client = new DefaultFaceClient(API_KEY, API_SEC);
    }

    public Single<Photo> detect (File imageFile) {
        return Single.fromCallable(() -> client.detect(imageFile));
    }
}
