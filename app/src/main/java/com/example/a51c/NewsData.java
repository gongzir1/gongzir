package com.example.a51c;

import android.widget.ImageButton;

public class NewsData {
    private ImageButton newsImage;
    private String newsName;
    private int id;

    public NewsData(String newsName, ImageButton newsImage, int id){
        this.newsName = newsName;
        this.newsImage = newsImage;
        this.id = id;

    }

    public String getnewsName(){
        return newsName;
    }
    public void set(String nameIn){
        this.newsName = nameIn;

    }

    public ImageButton getnewsImage(){
        return newsImage;
    }
    public void set(ImageButton ImageIn){
        this.newsImage = ImageIn;
    }

    public int getnewsId(){
        return this.id;
    }
    public void setnewsId(int idIn){
        this.id = idIn;
    }

}
