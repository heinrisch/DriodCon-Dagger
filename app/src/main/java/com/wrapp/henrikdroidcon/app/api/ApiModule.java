package com.wrapp.henrikdroidcon.app.api;

import com.wrapp.henrikdroidcon.app.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by henrik on 6/11/14.
 */
@Module(
        injects = MainActivity.class
)

public final class ApiModule {

    @Provides
    @Singleton
    Api provideApi() {
        return new Api();
    }

}
