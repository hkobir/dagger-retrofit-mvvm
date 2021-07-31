package com.example.dagger_retrofit_mvvm;

import android.app.Application;

import com.example.dagger_retrofit_mvvm.di.AlbumComponent;
import com.example.dagger_retrofit_mvvm.di.AlbumModule;
import com.example.dagger_retrofit_mvvm.di.DaggerAlbumComponent;

public class MyApplication extends Application {
    AlbumComponent albumComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        albumComponent = DaggerAlbumComponent.builder()
                .albumModule(new AlbumModule()).build();
    }

    public AlbumComponent getAlbumComponent() {
        return albumComponent;
    }
}
