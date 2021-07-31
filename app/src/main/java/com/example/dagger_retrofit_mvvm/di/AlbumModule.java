package com.example.dagger_retrofit_mvvm.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AlbumModule {
    private String baseUrl = "https://jsonplaceholder.typicode.com";

    @Singleton
    @Provides
    public AlbumServiceInterface getAlbumServiceInterface(Retrofit retrofit) {
        return retrofit.create(AlbumServiceInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetrofitInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
