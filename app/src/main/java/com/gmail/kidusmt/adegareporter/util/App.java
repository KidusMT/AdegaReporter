package com.gmail.kidusmt.adegareporter.util;

import android.app.Application;


import com.gmail.kidusmt.adegareporter.data.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Created by KidusMT on 12/29/2017.
 */

public class App extends Application {

    public static BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        // do this once, for example in your Application class

        //Initializes ObjectBox for the first time when application runs
        if (boxStore == null) boxStore = MyObjectBox.builder().androidContext(App.this).build();

    }
}
