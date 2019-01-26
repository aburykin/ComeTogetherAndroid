package ru.bur.lifeofflineandroid.util;

import android.util.Log;

public class MainLogger {

    private static String APP_LOG_TAG = "life_offline_android";

    public static void error(String tag, String message){
        StringBuilder sb = new StringBuilder();
        sb.append(APP_LOG_TAG).append(".").append(tag);
        Log.e(sb.toString(), message);
    }

    public static void debug(String tag, String message){
        StringBuilder sb = new StringBuilder();
        sb.append(APP_LOG_TAG).append(".").append(tag);
        Log.d(sb.toString(), message);
    }


    public static void info(String tag, String message){
        StringBuilder sb = new StringBuilder();
        sb.append(APP_LOG_TAG).append(".").append(tag);
        Log.i(sb.toString(), message);
    }

}
