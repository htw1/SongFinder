
package com.htw.songfinder.models.lastFmModel;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ArtistLastFm {

    @Expose
    private List<Image> image;
    @Expose
    private String listeners;
    @Expose
    private String mbid;
    @Expose
    private String name;
    @Expose
    private String streamable;
    @Expose
    private String url;

    public List<Image> getImage() {
        return image;
    }

    public Image getImageClass() {
        return imageClass;
    }

    private Image imageClass;

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
