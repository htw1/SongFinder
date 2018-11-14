package com.htw.songfinder.models;
import android.util.Pair;
import com.htw.songfinder.models.iTuneModel.ResultiTune;
import com.htw.songfinder.models.iTuneModel.RootiTune;
import com.htw.songfinder.models.lastFmModel.ArtistLastFm;
import com.htw.songfinder.models.lastFmModel.RootLastFm;
import java.util.ArrayList;
import java.util.List;


public class CombinedResult {

    List<Pair<String,String>> nameAndPhotoResults;
    private final RootiTune ituneResult;
    private final RootLastFm lastFmResult;

    public CombinedResult(RootiTune ituneResult, RootLastFm lastFmResult) {
        this.ituneResult = ituneResult;
        this.lastFmResult = lastFmResult;
    }

    public List<Pair<String,String>> getArtistsWithPhoto () {

        nameAndPhotoResults = new ArrayList<>() ;

        if (ituneResult.getListSongs() != null){
            for (ResultiTune item : ituneResult.getListSongs() ){
                nameAndPhotoResults.add(new Pair <String,String> (item.getArtistName(),item.getArtworkUrl100() ));
            }
        }
        if(lastFmResult.getResults().getArtistmatches().getListOfLatsFmArtists() != null) {
            for (ArtistLastFm item : lastFmResult.getResults().getArtistmatches().getListOfLatsFmArtists())
                nameAndPhotoResults.add(new Pair<String, String>(item.getName(),item.getImage().get(4).getText()));
        }
        return nameAndPhotoResults;
    }

}
