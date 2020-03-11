package com.leaf.zapperdisplaydata.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.leaf.zapperdisplaydata.data.remote.PersonsUpdateImpl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class PersonsWorker extends Worker {

    private String apiCallResponse;
    private static final String PERSONS_API = "http://demo9790103.mockable.io/persons";

    public PersonsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        connectToPersonsAPI(PERSONS_API);

        return Worker.Result.success();
    }

    void connectToPersonsAPI(String url) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;

        try {
            response = client.newCall(request).execute();

            apiCallResponse = response.body().string();

            response.close();
        } catch (Exception e) {
            Log.e(PersonsWorker.class.getSimpleName(), e.getMessage(), e);
        } finally {
            if (response != null) {

                response.close();
            }
        }
        PersonsUpdateImpl.personsUpdateStringPublishSubject.onNext(apiCallResponse);
    }
}
