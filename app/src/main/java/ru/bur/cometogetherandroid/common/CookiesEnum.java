package ru.bur.cometogetherandroid.common;

public enum CookiesEnum {

    os("os"),
    token("token"),
    session_id("session_id");

    private String name;

    CookiesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

