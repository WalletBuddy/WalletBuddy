package com.dalin.my.walletbuddy;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;
import android.util.Log;


import com.dalin.my.walletbuddy.data.BudgetData;


public class ParseApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "DJN9Tzz3LznkbVUdAXD1pUnryOsg1kdrQTNDK76k", "SIYhQthxm7GRPQontuXJPTPmdiar7LrQ9qu0Y9li");

        ParseObject.registerSubclass(BudgetData.class);
        Log.i("Application", "Initialized!");
    }
}

