package com.openmedical.server.constants;

public enum Title {

    MR("Mr"),Mrs("Mrs"),MISS("Miss"),OTHER("Other");
    private String title;
    private Title(String title){
        this.title = title;
    }
    public static Title getTitle(String title){
        return Title.valueOf(title);
    }

    public String getTitle() {
        return title;
    }
}
