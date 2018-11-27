package com.htw.songfinder.viewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.htw.songfinder.MainApplication;
import com.htw.songfinder.models.CombinedResult;
import com.htw.songfinder.models.iTuneModel.RootiTune;
import com.htw.songfinder.models.lastFmModel.NewRootLastFm;
import com.htw.songfinder.network.Services.ServiceItune;
import com.htw.songfinder.network.Services.ServiceLastFm;
import com.htw.songfinder.network.procesDialog.ApiResponse;
import javax.inject.Inject;
import javax.inject.Named;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ViewModelApp extends ViewModel {

    //services
    private ServiceItune serviceItune;
    private ServiceLastFm serviceLastFm;

    @Inject
    @Named("apiLastFm")
    Retrofit apiLastFm;

    @Inject
    @Named("apiItune")
    Retrofit apiItune;


    // Rx
    private final CompositeDisposable disposables = new CompositeDisposable();

    // LifeData, public for Rx purpose -> doOnSubscribe
    public MutableLiveData<ApiResponse> responseLiveDataCombinedResult  = new MutableLiveData<ApiResponse>();

    public ViewModelApp() {

        MainApplication.component.inject(this);
        serviceItune = apiItune.create(ServiceItune.class);
        serviceLastFm = apiLastFm.create(ServiceLastFm.class);
        //default query
        getAll("Queen");

    }

    public Observable getMergedObservable (String query) {

        Observable<RootiTune> iTuneObservable =serviceItune.getItuneArtistNameRx2NoList(ServiceItune.API_ITUNE_BASE_FULL_URL,query);
        Observable<NewRootLastFm> lastFmObservable = serviceLastFm.searchArtistRx(ServiceLastFm.API_LAST_FM_FULL_URL, query, ServiceLastFm.KEY_LAST_FM,"json");

        return iTuneObservable.flatMap((Function<RootiTune, ObservableSource<CombinedResult>>) rootiTune -> Observable.just(rootiTune).zipWith(lastFmObservable,
                (rootiTune1, rootLastFm) -> new CombinedResult(rootiTune1, rootLastFm)));
    }

    public void getAll (String query){

        disposables.add(getMergedObservable(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //  do something (update the UI) before the task started
                .doOnSubscribe(disposable -> { responseLiveDataCombinedResult.setValue(ApiResponse.loading());
                })
                .subscribe((Consumer<CombinedResult>) combinedResult -> responseLiveDataCombinedResult.setValue(ApiResponse.success(combinedResult)),
                        (Consumer<Throwable>)throwable -> responseLiveDataCombinedResult.setValue(ApiResponse.error(throwable))
                )
        );
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
