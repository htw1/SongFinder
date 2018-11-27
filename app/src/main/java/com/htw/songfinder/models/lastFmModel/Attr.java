
package com.htw.songfinder.models.lastFmModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Attr {

    @SerializedName("artist")
    private String mArtist;
    @SerializedName("page")
    private String mPage;
    @SerializedName("perPage")
    private String mPerPage;
    @SerializedName("rank")
    private String mRank;
    @SerializedName("total")
    private String mTotal;
    @SerializedName("totalPages")
    private String mTotalPages;

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getPage() {
        return mPage;
    }

    public void setPage(String page) {
        mPage = page;
    }

    public String getPerPage() {
        return mPerPage;
    }

    public void setPerPage(String perPage) {
        mPerPage = perPage;
    }

    public String getRank() {
        return mRank;
    }

    public void setRank(String rank) {
        mRank = rank;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

    public String getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(String totalPages) {
        mTotalPages = totalPages;
    }

}
