package com.htw.songfinder.models.iTuneModel;
import android.support.annotation.Nullable;
import javax.annotation.Generated;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;


/*@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")*/
@AutoValue
public abstract class  ResultiTune {

    public static TypeAdapter<ResultiTune> resultiTuneTypeAdapter (Gson gson){
        return new AutoValue_ResultiTune.GsonTypeAdapter(gson);
    }

    @Nullable
    @SerializedName("artistName")
    public abstract String getArtistName();

    @Nullable
    @SerializedName("trackName")
    public abstract String getSongName();

    @Nullable
    @SerializedName("artworkUrl60")
    public abstract String getArtworkUrl60();

    @Nullable
    @SerializedName("artworkUrl100")
    public abstract String getArtworkUrl100();

    @Nullable
    @SerializedName("previewUrl")
    // for player
    public abstract String getPreviewUrl();



}
