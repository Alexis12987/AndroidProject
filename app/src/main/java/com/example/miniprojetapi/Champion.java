package com.example.miniprojetapi;

import java.io.Serializable;

public class Champion implements Serializable {
    private String key;
    private String name;
    private String title;
    private String image;

    public Champion( String key, String name, String title,String img) {

        this.key = key;
        this.name = name;
        this.title = title;
        this.image=img;
    }

    // Getter Methods

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return image;
    }





    // Setter Methods

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String toString(){ return name; }
}