
package com.htw.songfinder.models.lastFmModel;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Artistmatches {

    @Expose
    private List<ArtistLastFm> artist;

    public ArtistLastFm getArtistClassLastFm() {
        return artistClassLastFm;
    }

    private ArtistLastFm artistClassLastFm;



    @Override
    public String toString() {
        return "Artistmatches{" +
                "listOfLatsFmArtists=" + artist +
                '}';
    }

    public List<ArtistLastFm> getListOfLatsFmArtists() {
        return artist;
    }

    public void setListOfLatsFmArtists(List<ArtistLastFm> listOfLatsFmArtists) {
        this.artist = listOfLatsFmArtists;
    }

}
