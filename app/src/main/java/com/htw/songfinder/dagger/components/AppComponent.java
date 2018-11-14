package com.htw.songfinder.dagger.components;

import com.htw.songfinder.dagger.module.AppModule;
import com.htw.songfinder.viewModel.ViewModelApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules = {AppModule.class})
public interface AppComponent {
    void inject (ViewModelApp viewModel);
}
