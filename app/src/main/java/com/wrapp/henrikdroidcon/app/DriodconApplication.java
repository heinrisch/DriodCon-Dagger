package com.wrapp.henrikdroidcon.app;

import android.app.Application;

import com.wrapp.henrikdroidcon.app.api.ApiModule;

import dagger.ObjectGraph;

/**
 * Created by henrik on 6/11/14.
 */
public class DriodconApplication extends Application {

    private static DriodconApplication app;
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        mObjectGraph = ObjectGraph.create(new ApiModule());
    }

    public void inject(Object o){
        mObjectGraph.inject(o);
    }

    public static DriodconApplication get(){
        return app;
    }
}
