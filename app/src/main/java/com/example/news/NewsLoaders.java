package com.example.news;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

public class NewsLoaders extends AsyncTaskLoader<List<News>> {
    String url;
    public NewsLoaders(@NonNull Context context,String url) {
        super(context);
        this.url=url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Nullable
    @Override
    public List<News> loadInBackground() {
        return FetchData.fetchFromUrl(url);
    }


}
