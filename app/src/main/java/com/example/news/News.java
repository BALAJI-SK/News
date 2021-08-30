package com.example.news;



public class News {
    private final String Section,Title,date,webLink;

    public News(String section, String title, String date, String webLink) {
        Section = section;
        Title = title;
        this.date = date;

        this.webLink = webLink;
    }

    public String getDate() {
        int end =date.indexOf('T');

        return date.substring(0,end);
    }

    public String getSection() {
        return Section;
    }

    public String getTitle() {
        return Title;
    }

    public String getTime() {
        int start =date.indexOf('T');
        int end= date.indexOf('Z');
        return date.substring(start+1,end);
    }

    public String getWebLink() {
        return webLink;
    }
}
