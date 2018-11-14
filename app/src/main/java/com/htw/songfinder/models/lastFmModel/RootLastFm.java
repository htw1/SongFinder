package com.htw.songfinder.models.lastFmModel;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

import java.util.List;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RootLastFm {

    @Expose
    private Results results;

    public List<ArtistLastFm> getListOfLastFm;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
