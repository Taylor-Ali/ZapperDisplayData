package com.leaf.zapperdisplaydata.data.service;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class PersonsUpdateScheduler {
    private static final int INTERVAL = 15;

    public static void setupPeriodicWork() {

        Constraints myConstraints = new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresStorageNotLow(true)
                .build();

        Data source = new Data.Builder()
                .putString("workType", "PeriodicTime")
                .build();

        PeriodicWorkRequest periodicWorkRequest =
                new PeriodicWorkRequest.Builder(PersonsWorker.class, INTERVAL, TimeUnit.MINUTES)
                        .setConstraints(myConstraints)
                        .setInputData(source)
                        .build();

        WorkManager.getInstance().enqueue(periodicWorkRequest);

    }
}
