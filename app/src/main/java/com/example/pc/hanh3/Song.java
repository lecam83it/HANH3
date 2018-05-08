package com.example.pc.hanh3;

public class Song {
    private String Title;
    private int File;
     public Song(String title , int file){
         Title =title;
         File= file;

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
}