package ru.bur.lifeofflineandroid.util;

import org.junit.Test;

public class GameSessionTest { // TODO, можно удалить
    @Test
    public void testGameSession() {
        GameSession session = new GameSession();
        DaggerGameComponent.create().inject(session);
        assertEquals("Hello Dagger", session.data.hello);
    }
}