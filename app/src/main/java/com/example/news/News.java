package com.example.news;
public class News {
    private final String Section,Title,date,time;

    public News(String section, String title, String date,String time) {
        Section = section;
        Title = title;
        this.date = date;
        this.time=time;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return Section;
    }

    public String getTitle() {
        return Title;
    }

    public String getTime() {
        return time;
    }
}
