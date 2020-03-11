package com.leaf.zapperdisplaydata.data.repository.sharedpreference;

import android.content.SharedPreferences;

import com.leaf.zapperdisplaydata.data.repository.Preferences;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;

public class DatabasePreferencesImpl implements DatabasePreferences {
    private SharedPreferences sharedPreferences;

    public DatabasePreferencesImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Completable storeDatabaseFlag(Boolean isDataLoaded) {
        return new Completable() {
            @Override
            protected void subscribeActual(CompletableObserver s) {
                try {
                    sharedPreferences
                            .edit()
                            .putBoolean(Preferences.IS_PERSONS_CACHED.getValue(), isDataLoaded)
                            .commit();
                    s.onComplete();
                } catch (Throwable throwable) {
                    s.onError(throwable);
                }
            }
        };
    }

    @Override
    public Flowable<Boolean> retrieveDatabaseFlag() {
        return Flowable.create(e -> {
            try {
                Boolean isDataLoaded = sharedPreferences
                        .getBoolean(Preferences.IS_PERSONS_CACHED.getValue(), Boolean.FALSE);
                e.onNext(isDataLoaded);
                e.onComplete();
            } catch (Throwable throwable) {
                e.onError(throwable);
            }
        }, BackpressureStrategy.LATEST);
    }
}
