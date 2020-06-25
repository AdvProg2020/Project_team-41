package org.example;

public enum Music {
    MAIN_MENU("music"), USER_SECTION("UserSectionMusic"),OFF_SECTION("OffMusic");

    Music(String path) {
        this.name = path;
    }
    private String name;
    public String getName(){
        return name;
    }
}