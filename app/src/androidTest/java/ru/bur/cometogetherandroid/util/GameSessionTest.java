package ru.bur.cometogetherandroid.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameSessionTest { // TODO, можно удалить
    @Test
    public void testGameSession() {
        GameSession session = new GameSession();
        DaggerGameComponent.create().inject(session);
        assertEquals("Hello Dagger", session.data.hello);
    }
}