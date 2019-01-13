package ru.bur.cometogetherandroid.util;

import dagger.Module;
import dagger.Provides;

@Module
public class GameModule { // TODO, можно удалить

    @Provides
    GameData providesGameData() { return new GameData(); }

}
