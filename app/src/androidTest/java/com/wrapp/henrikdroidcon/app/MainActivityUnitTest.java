package com.wrapp.henrikdroidcon.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.mock.MockApplication;
import android.widget.TextView;

import com.wrapp.henrikdroidcon.app.api.Api;
import com.wrapp.henrikdroidcon.app.api.ApiMock;

import javax.inject.Singleton;

import dagger.ObjectGraph;
import dagger.Provides;


public class MainActivityUnitTest extends ActivityUnitTestCase<MainActivity> {


    private Context mContext;
    private Application mApplication;

    public MainActivityUnitTest() {
        super(MainActivity.class);
    }


    @dagger.Module(library = true,
            injects = {
                    MainActivity.class,
            })
    public static class TestModule {

        @Provides
        @Singleton
        Api provideApi() {
            return new ApiMock();
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = new ContextWrapper(getInstrumentation().getTargetContext()) {
            @Override
            public Context getApplicationContext() {
                return mApplication;
            }
        };
        mApplication = new MockApp();
    }

    public void testActivityCreated() {
        Intent intent = new Intent(mContext, MainActivity.class);
        setActivityContext(mContext);
        startActivity(intent, null, null);
        assertNotNull(getActivity());

        TextView tv = (TextView) getActivity().findViewById(R.id.text);
        assertEquals("Hello Tests!", tv.getText());
    }

    class MockApp extends MockApplication implements Injectable {
        private ObjectGraph mObjectGraph;

        public MockApp(){
            mObjectGraph = ObjectGraph.create(new TestModule());
        }

        @Override
        public void inject(Object o){
            mObjectGraph.inject(o);
        }
    }
}
