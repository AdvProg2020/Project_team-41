package org.example;

public enum Music {
    MAIN_MENU("music");


    Music(String path) {
        this.name = path;
    }
    private String name;
    public String getName(){
        return name;
    }
}