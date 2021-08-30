package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
    }
static  class ViewHolderItem{
    TextView title,section, date,time,author;
}
    @NonNull
    @Override
    public View getView(int position, @Nullable View ListView, @NonNull ViewGroup parent) {
        ViewHolderItem viewHolderItem;
        if(ListView==null){
            ListView= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            viewHolderItem  = new ViewHolderItem();
            viewHolderItem.date=ListView.findViewById(R.id.date);
            viewHolderItem.time=ListView.findViewById(R.id.time);
            viewHolderItem.section=ListView.findViewById(R.id.section);
            viewHolderItem.title=ListView.findViewById(R.id.title);
            viewHolderItem.author=ListView.findViewById(R.id.author);
            ListView.setTag(viewHolderItem);
        }else {
            viewHolderItem=(ViewHolderItem) ListView.getTag();
        }
         News currentNews= getItem(position);
        viewHolderItem.title.setText(currentNews.getTitle());
        viewHolderItem.section.setText(currentNews.getSection());
        viewHolderItem.date.setText(currentNews.getDate());
        viewHolderItem.time.setText(currentNews.getTime());
       viewHolderItem.author.setText(currentNews.getAuthor());
        return ListView;
    }
}
