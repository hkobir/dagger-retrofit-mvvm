package com.example.dagger_retrofit_mvvm.di;

import com.example.dagger_retrofit_mvvm.model.AlbumData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface AlbumServiceInterface {
    @GET("/albums/{id}/photos")
    Call<List<AlbumData>> getAlbumApiData(@Path("id") int id);
}
