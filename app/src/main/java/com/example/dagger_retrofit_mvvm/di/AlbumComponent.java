package com.example.dagger_retrofit_mvvm.di;

import com.example.dagger_retrofit_mvvm.viewmodel.MainActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AlbumModule.class})
public interface AlbumComponent {
    public void inject(MainActivityViewModel viewModel);
}
