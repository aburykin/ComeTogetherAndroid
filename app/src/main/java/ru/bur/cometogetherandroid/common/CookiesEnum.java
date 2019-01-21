package ru.bur.cometogetherandroid.common;

public enum CookiesEnum {

    os("os"),
    token("user_token"),
    user_id("user_id"); // идентификатор пользователя на сервере //TODO закодировать его и в БД перенести

    private String name;

    CookiesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

