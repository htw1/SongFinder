package com.htw.songfinder.network.Services;
import com.htw.songfinder.models.lastFmModel.RootLastFm;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ServiceLastFm {

    String API_LAST_FM_BASE_ERL = "http://ws.audioscrobbler.com/";
    String API_LAST_FM_FULL_URL = "http://ws.audioscrobbler.com/2.0/?method=artist.search";
    String KEY_LAST_FM = "aff4c3b291822c232e266556f9570a23";

    // 2.0/?method=artist.search&artist=jackson&api_key=aff4c3b291822c232e266556f9570a23&format=json

    @GET
    Observable<RootLastFm> searchArtistRx(
            @Url String url,
            @Query("artist") String artistName,
            @Query("api_key") String key,
            @Query("format") String formatType
    );

}
