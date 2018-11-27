
package com.htw.songfinder.models.lastFmModel;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Image {

    @SerializedName("size")
    private String mSize;
    @SerializedName("#text")
    private String mText;

    public String getSize(String extralarge) {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

}
