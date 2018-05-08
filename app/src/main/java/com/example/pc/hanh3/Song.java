package com.example.pc.hanh3;

import java.io.Serializable;

public class Song implements Serializable {
    private String Title;
    private int File;
    private int Image;
     public Song(String title , int file, int image){
         Title =title;
         File= file;
         Image = image;
     }

    public String getTitle() {
        return Title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}