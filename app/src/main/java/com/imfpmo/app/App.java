package com.imfpmo.app;

import android.app.Application;

import com.bugfender.sdk.Bugfender;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Bugfender.init(this, "JAPF4gBVPzvowURq1obVtUxMIzdaAtlH", BuildConfig.DEBUG);
        Bugfender.enableCrashReporting();
        Bugfender.enableUIEventLogging(this);
        Bugfender.enableLogcatLogging();
    }
}
