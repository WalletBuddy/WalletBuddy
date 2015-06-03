package com.dalin.mywallet.walletbuddy;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;
import android.util.Log;


import com.dalin.mywallet.walletbuddy.data.BudgetData;
import com.dalin.mywallet.walletbuddy.data.CategoryData;
import com.dalin.mywallet.walletbuddy.data.CategoryExpenses;

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
        ParseObject.registerSubclass(CategoryData.class);
        ParseObject.registerSubclass(CategoryExpenses.class);


        Log.i("Application", "Initialized!");
    }
}

