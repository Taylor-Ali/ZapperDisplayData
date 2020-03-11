package com.leaf.zapperdisplaydata.data.repository.sharedpreference;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface DatabasePreferences {
    Completable storeDatabaseFlag(Boolean isDataLoaded);

    Flowable<Boolean> retrieveDatabaseFlag();
}
