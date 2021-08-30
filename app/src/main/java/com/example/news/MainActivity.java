package com.example.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    List<News> newsList;
    String link="https://content.guardianapis.com/search?q=sports&api-key=test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
        newsList=FetchData.fetchFromUrl(link);
        runOnUiThread(() -> {
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