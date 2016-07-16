package com.hackprague.sportcity.utilities;

import android.util.Log;

import com.hackprague.sportcity.Config;

/**
 * Created by jakubveverka on 16.07.16.
 */
public class Logger {

    public static String TAG = "SportCity";

    public static void d(String message) {
        if(Config.DEBUG) Log.d(TAG, message);
    }

    public static void d(String tag, String message) {
        if(Config.DEBUG) Log.d(TAG + " " + tag, message);
    }
}
