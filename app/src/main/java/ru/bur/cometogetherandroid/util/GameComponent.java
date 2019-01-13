package ru.bur.cometogetherandroid.util;

import dagger.Component;

@Component(modules = GameModule.class) // TODO, можно удалить
public interface GameComponent {
    void inject(GameSession obj);
}