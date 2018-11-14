package com.htw.songfinder;
import android.app.Application;
import com.htw.songfinder.dagger.components.AppComponent;
import com.htw.songfinder.dagger.components.DaggerAppComponent;
import com.htw.songfinder.dagger.module.AppModule;

public class MainApplication extends Application {

    public static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}


