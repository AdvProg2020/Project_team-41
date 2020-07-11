package org.example;

public enum Music {
    MAIN_MENU("music"), USER_SECTION("UserSectionMusic"),OFF_SECTION("OffMusic"),All_Products("allProducts");

    Music(String path) {
        this.name = path;
    }
    private final String name;
    public String getName(){
        return name;
    }
}