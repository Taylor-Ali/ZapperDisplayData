package com.leaf.zapperdisplaydata.service;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class PersonsUpdateScheduler {
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
                new PeriodicWorkRequest.Builder(PersonsWorker.class, 30, TimeUnit.MINUTES)
                        .setConstraints(myConstraints)
                        .setInputData(source)
                        .build();

        WorkManager.getInstance().enqueue(periodicWorkRequest);

    }
}
