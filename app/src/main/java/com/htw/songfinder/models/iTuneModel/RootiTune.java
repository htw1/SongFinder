package com.htw.songfinder.models.iTuneModel;
import android.support.annotation.Nullable;
import java.util.List;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/*@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")*/
@AutoValue
public abstract class  RootiTune {

    public static TypeAdapter<RootiTune> typeAdapter(Gson gson){
        return new AutoValue_RootiTune.GsonTypeAdapter(gson);
    }
    @Nullable
    @SerializedName("resultCount")
    public abstract Integer getListSize();

    @Nullable
    @SerializedName("results")
    public abstract List<ResultiTune> getListSongs();

    @Nullable
    @SerializedName("errorMessage")
    public abstract String getErrorMessage();

}
