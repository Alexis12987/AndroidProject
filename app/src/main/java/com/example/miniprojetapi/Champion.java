package com.example.miniprojetapi;

public class Champion {
    private String key;
    private String name;
    private String title;

    public Champion( String key, String name, String title) {

        this.key = key;
        this.name = name;
        this.title = title;
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



}