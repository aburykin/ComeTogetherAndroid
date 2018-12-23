package ru.bur.cometogetherandroid.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Получает общий список запросов. И посылает их
 */
public class AppClient {

    //   static String baseUrl ="http://10.0.2.2:8080/profile/";
    static String baseUrl = "http://192.168.0.102:8080/";

    static Map<String, String> AllServerLables = new HashMap<>(); // label

    static {
        AllServerLables.put("meetings", "meetings");
        AllServerLables.put("appUsers", "appUsers");
    }

  /*  public void getAllUrls(){


    }*/

    public static String getUrlByLabel(String label) {
        return baseUrl + label;
    }


}
