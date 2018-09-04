package by.client.android.railwayapp;

import javax.inject.Singleton;

import android.app.Application;
import by.client.android.railwayapp.support.database.room.SearchTrainCacheManager;
import dagger.Module;
import dagger.Provides;

/**
 * Модуль для работы с кэшем приложения
 *
 * @author PRV
 */
@Module
class ApplicationCacheModule {

    private final Application application;

    ApplicationCacheModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SearchTrainCacheManager getTrainSearchDb() {
        return new SearchTrainCacheManager(application);
    }
}
