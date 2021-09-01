package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    ProgressBar indicator;
    TextView noInternet;
    NewsAdapter newsAdapter;
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

             getSupportLoaderManager().initLoader(Constant.NEWS_LOADER_ID, null, this);

            indicator.setVisibility(View.INVISIBLE);
           newsAdapter = new NewsAdapter(MainActivity.this,new ArrayList<>());
            ListView listView= findViewById(R.id.list_view);

            listView.setAdapter(newsAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                News currentNews =newsAdapter.getItem(position);
                Intent website =new Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.getWebLink()));
                startActivity(website);
            });

    }
    }

    private boolean checkConnectivity(ConnectivityManager cm) {
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();}

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {

        return new NewsLoaders(MainActivity.this,CreateLink());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> data) {
indicator.setVisibility(View.INVISIBLE);
newsAdapter.clear();
if(data !=null){
    newsAdapter.addAll(data);
}else{
    Log.e(Constant.LOG_MSG, "Data not available... ");
    noInternet.setVisibility(View.VISIBLE);
}
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
       newsAdapter.clear();
    }
    private  String CreateLink(){
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);
        // getString retrieve a String value for the max News item
        String maxNews = sharedPreferences.getString(
                getString(R.string.settings_max_news_key),
                getString(R.string.settings_max_news_default));
        // getString retrieve a String value for Order-By item
        String orderBy = sharedPreferences.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));
        // parse breaks apart the URI string
        Uri baseUri = Uri.parse(Constant.BASE_URL);
        // buildUpon prepares the base Uri
        Uri.Builder builder = baseUri.buildUpon();
        builder.appendQueryParameter(Constant.KEY_SHOW_TAGS, Constant.KEY_CONTRIBUTOR);
        builder.appendQueryParameter(Constant.KEY_ORDER_BY, orderBy);
        builder.appendQueryParameter(Constant.KEY_PAGE_SIZE, maxNews);
        builder.appendQueryParameter(Constant.API_KEY, Constant.KEY_TEST);

     

       return builder.toString();
       //return link;
    }


    /**
     * @param menu The options menu which display the menu item.
     * @return true for the menu to be displayed;
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    /**
     * @param item The menu item that was selected.
     * @return boolean Return true to allow
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}