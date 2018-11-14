package com.htw.songfinder.dagger.module;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htw.songfinder.network.AutoValueGsonFactory;
import com.htw.songfinder.network.Services.ServiceItune;
import com.htw.songfinder.network.Services.ServiceLastFm;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    private final Context appContext;

    public AppModule(Context appContext){
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor loggingInterceptor() {
            return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    public Gson gsonBuilder(){
        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {


        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .build();
                    return chain.proceed(request);
                })
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    @Named("apiItune")
    public Retrofit apiItune(OkHttpClient okHttpClient, Gson gson){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ServiceItune.API_ITUNE_BASE_URL)
                .client(okHttpClient)
                .build();
    }


    @Provides
    @Singleton
    @Named("apiLastFm")
    public Retrofit apiLastFm(OkHttpClient okHttpClient, Gson gson){

        return new Retrofit.Builder()
                .baseUrl(ServiceLastFm.API_LAST_FM_BASE_ERL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}