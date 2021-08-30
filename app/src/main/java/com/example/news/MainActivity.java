package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ProgressBar indicator;
    TextView noInternet;
    List<News> newsList;
    String link="https://content.guardianapis.com/search?q=sports&show-tags=contributor&api-key=test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noInternet=findViewById(R.id.No_connection);
        indicator=findViewById(R.id.indicator_progress);
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         boolean isConnected= checkConnectivity(cm);
         if(!isConnected){
             noInternet.setVisibility(View.VISIBLE);
             indicator.setVisibility(View.INVISIBLE);
         }else {
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
        newsList=FetchData.fetchFromUrl(link);
        runOnUiThread(() -> {
            indicator.setVisibility(View.INVISIBLE);
            NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this,newsList);
            ListView listView= findViewById(R.id.list_view);

            listView.setAdapter(newsAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                News currentNews =newsAdapter.getItem(position);
                Intent website =new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getWebLink()));
                startActivity(website);
            });
        });
        });
    }
    }

    private boolean checkConnectivity(ConnectivityManager cm) {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();}
}