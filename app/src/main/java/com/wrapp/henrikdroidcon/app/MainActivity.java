package com.wrapp.henrikdroidcon.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wrapp.henrikdroidcon.app.api.Api;

import javax.inject.Inject;


public class MainActivity extends Activity {

    @Inject Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DriodconApplication.getInjectable(this).inject(this);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(api.getTweet());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
