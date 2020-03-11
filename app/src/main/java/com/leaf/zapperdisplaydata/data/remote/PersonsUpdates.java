package com.leaf.zapperdisplaydata.data.remote;

import io.reactivex.Flowable;

public interface PersonsUpdates {
    Flowable<String> onPersonsUpdate();
}
