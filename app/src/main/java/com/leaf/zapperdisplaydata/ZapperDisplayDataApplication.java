package com.leaf.zapperdisplaydata;

import android.app.Application;

import com.leaf.zapperdisplaydata.di.ApplicationComponent;
import com.leaf.zapperdisplaydata.di.DaggerApplicationComponent;
import com.leaf.zapperdisplaydata.di.common.AndroidModule;

public class ZapperDisplayDataApplication extends Application {
    private static ApplicationComponent component;

    @Override
    public void onCreate() {

        super.onCreate();

        setupDependencyInjection();

    }

    private void setupDependencyInjection() {
        component = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
