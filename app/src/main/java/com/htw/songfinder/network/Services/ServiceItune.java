package com.htw.songfinder.network.Services;
import com.htw.songfinder.models.iTuneModel.RootiTune;
import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ServiceItune {

    String API_ITUNE_BASE_URL = "https://itunes.apple.com/";
    String API_ITUNE_BASE_FULL_URL = "https://itunes.apple.com/search";

    @GET
    Observable<RootiTune> getItuneArtistNameRx2NoList(
            @Url String url,
            @Query("term") String artisName);

}
