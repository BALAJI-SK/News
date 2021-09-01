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


    public static List<News> fetchFromUrl(String link){
        URL url =createUrl(link);
        String jsonResponse="";
try {
    jsonResponse=makeHTTPRequest(url);
}catch (Exception ignored){
    Log.e(Constant.LOG_MSG, "Data Fetch Form HTTP Cannot be done");
}
        assert jsonResponse != null;
        return extractFromString(jsonResponse);
    }

    private static List<News> extractFromString(String jsonResponse) {
        if(jsonResponse.equals(""))return null;
        List<News> newsList=new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject rootResponse= root.optJSONObject(Constant.JSON_KEY_RESPONSE);

            assert rootResponse != null;
            JSONArray results = rootResponse.optJSONArray(Constant.JSON_KEY_RESULTS);
            for(int i=0;results !=null && i<results.length();i++){
                JSONObject currentNews= results.optJSONObject(i);
                String section =currentNews.optString(Constant.JSON_KEY_SECTION_NAME);
                String date= currentNews.optString(Constant.JSON_KEY_WEB_PUBLICATION_DATE);
                String title = currentNews.optString(Constant.JSON_KEY_WEB_TITLE);
                String webLink= currentNews.optString( Constant.JSON_KEY_WEB_URL);
                JSONArray tags =currentNews.optJSONArray(Constant.JSON_KEY_TAGS);
                String author="";
                if(tags!=null){
                    for(int j=0;j< tags.length();j++){
                   JSONObject tagsObject= tags.optJSONObject(j);
                   author=tagsObject.optString(Constant.JSON_KEY_AUTHOR);
                }}else{Log.e(Constant.LOG_MSG, "Author not found");}
                newsList.add(new News(section, title, date, webLink, author));
            }

        }catch (JSONException ignore){
        Log.e(Constant.LOG_MSG, "Unable to fetch data from String");
        }
   return newsList;}

    private static String makeHTTPRequest(URL url) {
        String jsonResponse="";
        HttpURLConnection urlConnection;
        InputStream inputStream;
        if(url==null) {
            Log.e(Constant.LOG_MSG, "URL is NULL");
            return null;}
            try {
             urlConnection= (HttpURLConnection) url.openConnection() ;
             urlConnection.setConnectTimeout(Constant.CONNECT_TIMEOUT);
             urlConnection.setReadTimeout(Constant.READ_TIMEOUT);
             urlConnection.setRequestMethod(Constant.REQUEST_METHOD);
             urlConnection.connect();
             if(urlConnection.getResponseCode()==Constant.RESPONSE_CODE){
                 inputStream= urlConnection.getInputStream();
                 jsonResponse=getInfoFromInputStream(inputStream);
             }
            } catch (IOException e) {
                e.printStackTrace();
            }
   return jsonResponse; }

    private static String getInfoFromInputStream(InputStream inputStream) {
        if(inputStream==null){Log.e(Constant.LOG_MSG, "NULl in InputStream");return null;
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
            Log.e(Constant.LOG_MSG, "Could not read data");
        }
    return output.toString();}

    private static URL createUrl(String link) {
        URL url=null;
        if(link==null||link.equals(""))return null;
        try {
            url = new URL(link);
        }
        catch (MalformedURLException ignored){
            Log.e(Constant.LOG_MSG, "URL Cannot be Created");
        }
        return url;
    }

}
