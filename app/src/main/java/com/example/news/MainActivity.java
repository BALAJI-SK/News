package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<News> newsList =new ArrayList<>();
        newsList.add(new News("Sports","Balaji won", "22 Mar, 2021", "3:00 PM"));
        newsList.add(new News("Politics", "Hi All", "22 Mar,2021", "3:00 PM"));
        newsList.add(new News("Sports","Balaji won", "22 Mar, 2021", "3:00 PM"));
        newsList.add(new News("Politics", "Hi All", "22 Mar,2021", "3:00 PM"));
        newsList.add(new News("Sports","Balaji won", "22 Mar, 2021", "3:00 PM"));
        newsList.add(new News("Politics", "Hi All", "22 Mar,2021", "3:00 PM"));
        NewsAdapter newsAdapter = new NewsAdapter(this,newsList);
        ListView listView= findViewById(R.id.list_view);
        listView.setAdapter(newsAdapter);
    }
}