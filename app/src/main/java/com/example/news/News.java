package com.example.news;



public class News {
    private final String section,title,date,webLink,author;

    public News(String section, String title, String date, String webLink, String author) {
        this.section = section;
        this.title = title;
        this.date = date;
        this.webLink = webLink;

        this.author = author;
    }

    public String getDate() {
        int end =date.indexOf('T');

        return date.substring(0,end);
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {

        return title;

    }

    public String getTime() {
        int start =date.indexOf('T');
        int end= date.lastIndexOf(':');
        return date.substring(start+1,end);
    }

    public String getWebLink() {
        return webLink;
    }
    public String getAuthor(){
       if(author.equals(""))return Constant.No_Author;

       return author;
    }
}
