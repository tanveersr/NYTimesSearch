package com.trandhawa.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by trandhawa on 9/24/17.
 */

public class Article {

    String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    String headline;
    String thumbNail;
    private static String NYTIMES_URL = "http://www.nytimes.com/";

    public Article(JSONObject jsonObject) {
        try{
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getString("headline");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if(multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                this.thumbNail = NYTIMES_URL + multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Article> fromJsonArray(JSONArray array){

        ArrayList<Article> results = new ArrayList<Article>();

        for(int i=0; i<array.length(); i++){
            try{
                results.add(new Article(array.getJSONObject(i)));
            } catch(JSONException e){
                e.printStackTrace();
            }
        }

        return results;
    }
}
