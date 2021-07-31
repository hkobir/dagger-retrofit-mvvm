package com.example.dagger_retrofit_mvvm.viewmodel;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dagger_retrofit_mvvm.MyApplication;
import com.example.dagger_retrofit_mvvm.di.AlbumServiceInterface;
import com.example.dagger_retrofit_mvvm.model.AlbumData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    @Inject
    AlbumServiceInterface albumServiceInterface;


    private MutableLiveData<List<AlbumData>> albumList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        ((MyApplication) application).getAlbumComponent().inject(MainActivityViewModel.this);
        albumList = new MutableLiveData<>();
    }

    public MutableLiveData<List<AlbumData>> getAlbumListObserver() {
        return albumList;
    }

    public void makeAlbumApiCall(int albumId) {
        Call<List<AlbumData>> call = albumServiceInterface.getAlbumApiData(albumId);
        call.enqueue(new Callback<List<AlbumData>>() {
            @Override
            public void onResponse(Call<List<AlbumData>> call, Response<List<AlbumData>> response) {
                if (response.isSuccessful()) {
                    albumList.postValue(response.body());
                } else {
                    albumList.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<AlbumData>> call, Throwable t) {
                albumList.postValue(null);
            }
        });
    }
}
