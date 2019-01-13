package ru.bur.cometogetherandroid.util;

import dagger.Component;

@Component(modules = GameModule.class)
public interface GameComponent {
    void inject(GameSession obj);
}