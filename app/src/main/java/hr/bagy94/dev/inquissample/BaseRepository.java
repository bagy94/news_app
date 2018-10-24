package hr.bagy94.dev.inquissample;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import hr.bagy94.dev.inquissample.utils.OnApiServiceFinished;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseRepository {
    private static final String TAG = "BaseRepository";

    public <R> MutableLiveData<R> callForLiveData(Call<R> responseCall) {
        final MutableLiveData<R> responseMutableLiveData = new MutableLiveData<>();
        callForData(responseCall, new OnApiServiceFinished<R>() {
            @Override
            public void onSuccessfulApiService(R response) {
                responseMutableLiveData.setValue(response);
            }
        });
        return responseMutableLiveData;
    }

    protected <R> void callForData(Call<R> call, final OnApiServiceFinished<R> listener) {
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccessfulApiService(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                Log.d(TAG, "service failed");
            }
        });
    }
}
