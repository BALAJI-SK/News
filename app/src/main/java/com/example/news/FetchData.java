package com.example.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public  class FetchData {
  private static final String LOG_MSG=  "FetchData Error";

    public static List<News> fetchFromUrl(String link){
        URL url =createUrl(link);
        String jsonResponse="";
try {
    jsonResponse=makeHTTPRequest(url);
}catch (Exception ignored){
    Log.e(LOG_MSG, "Data Fetch Form HTTP Cannot be done");
}
        assert jsonResponse != null;
        return extractFromString(jsonResponse);
    }

    private static List<News> extractFromString(String jsonResponse) {
        if(jsonResponse.equals(""))return null;
        List<News> newsList=new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject rootResponse= root.optJSONObject("response");

            assert rootResponse != null;
            JSONArray results = rootResponse.optJSONArray("results");
            for(int i=0;results !=null && i<results.length();i++){
                JSONObject currentNews= results.optJSONObject(i);
                String section =currentNews.optString("sectionName");
                String date= currentNews.optString("webPublicationDate");
                String title = currentNews.optString("webTitle");
                String webLink= currentNews.optString( "webUrl");
                newsList.add(new News(section, title, date, webLink));
            }

        }catch (JSONException ignore){
        Log.e(LOG_MSG, "Unable to fetch data from String");
        }
   return newsList;}

    private static String makeHTTPRequest(URL url) {
        String jsonResponse="";
        HttpURLConnection urlConnection;
        InputStream inputStream;
        if(url==null) {
            Log.e(LOG_MSG, "URL is NULL");
            return null;}
            try {
             urlConnection= (HttpURLConnection) url.openConnection() ;
             urlConnection.setConnectTimeout(2000);
             urlConnection.setReadTimeout(1000);
             urlConnection.setRequestMethod("GET");
             urlConnection.connect();
             if(urlConnection.getResponseCode()==200){
                 inputStream= urlConnection.getInputStream();
                 jsonResponse=getInfoFromInputStream(inputStream);
             }
            } catch (IOException e) {
                e.printStackTrace();
            }
   return jsonResponse; }

    private static String getInfoFromInputStream(InputStream inputStream) {
        if(inputStream==null){Log.e(LOG_MSG, "NULl in InputStream");return null;
        }
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder output=new StringBuilder();

        try {
            String line = bufferedReader.readLine();
            while (line!=null){
                output.append(line);
                line=bufferedReader.readLine();
            }
        }catch (IOException e){
            Log.e(LOG_MSG, "Could not read data");
        }
    return output.toString();}

    private static URL createUrl(String link) {
        URL url=null;
        if(link==null||link.equals(""))return null;
        try {
            url = new URL(link);
        }
        catch (MalformedURLException ignored){
            Log.e(LOG_MSG, "URL Cannot be Created");
        }
        return url;
    }

}
