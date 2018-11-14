package com.htw.songfinder.network.procesDialog;
import com.htw.songfinder.models.CombinedResult;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import static com.htw.songfinder.network.procesDialog.Status.ERROR;
import static com.htw.songfinder.network.procesDialog.Status.LOADING;
import static com.htw.songfinder.network.procesDialog.Status.SUCCESS;

public class ApiResponse {

    public final Status status;

    @Nullable
    public final CombinedResult combinedResult;

    @Nullable
    public final Throwable error;

    public ApiResponse(Status status, @Nullable CombinedResult combinedResult, @Nullable Throwable error) {
        this.status = status;
        this.combinedResult = combinedResult;
        this.error = error;
    }

    public static ApiResponse loading( ) {

        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull CombinedResult combinedResult) {
        return new ApiResponse(SUCCESS, combinedResult, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}

