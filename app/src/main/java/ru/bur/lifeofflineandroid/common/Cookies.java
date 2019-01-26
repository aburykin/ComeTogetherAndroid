package ru.bur.lifeofflineandroid.common;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;
import static android.content.SharedPreferences.Editor;

public class  Cookies {

    private static String REF = "cookies";
    @Inject
    public Context context;

    private SharedPreferences sharedPreferences;


    public Cookies(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(REF, MODE_PRIVATE);
    }

    public void set(String key, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }

    private void init() {
        set(CookiesEnum.os.getName(), "android");
    }

    public Long getUserId(){
        return Long.valueOf(get(CookiesEnum.user_id.toString()));
    }


}

