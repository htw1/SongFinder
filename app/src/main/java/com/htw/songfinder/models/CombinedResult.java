package com.htw.songfinder.models;
import android.util.Pair;
import com.annimon.stream.Collectors;
import com.htw.songfinder.models.iTuneModel.RootiTune;
import com.htw.songfinder.models.lastFmModel.NewRootLastFm;
import java.util.ArrayList;
import java.util.List;

import static com.annimon.stream.Collectors.toCollection;
import static com.annimon.stream.Collectors.toList;

public class CombinedResult {

    List<Pair<String,String>> nameAndPhotoResults =  new ArrayList<>() ;

    private final RootiTune ituneResult;
    private final NewRootLastFm lastFmResult;

    public CombinedResult(RootiTune ituneResult, NewRootLastFm lastFmResult) {
        this.ituneResult = ituneResult;
        this.lastFmResult = lastFmResult;
    }

    public List<Pair<String,String>> getArtistsWithPhoto () {
        nameAndPhotoResults = new ArrayList<>() ;

        nameAndPhotoResults.addAll(com.annimon.stream.Stream.of(ituneResult.getListSongs())
                .map(item->new Pair <String,String> (item.getSongName(),item.getArtworkUrl100() ))
                .collect(Collectors.toList()));
        nameAndPhotoResults.addAll(com.annimon.stream.Stream.of(lastFmResult.getToptracks().getTrack())
                .map(item-> new Pair<String,String>(item.getName(),item.getImage().get(3).getText()))
                .collect(Collectors.toList()));

        return nameAndPhotoResults;
    }
}
