package ru.bur.cometogetherandroid.common;

public enum CookiesEnum {

    os("os"),
    token("user_token");

    private String name;

    CookiesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

