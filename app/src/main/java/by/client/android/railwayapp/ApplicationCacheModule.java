package by.client.android.railwayapp;

import javax.inject.Singleton;

import android.app.Application;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.traintimetable.history.ObjectHistory;
import by.client.android.railwayapp.ui.traintimetable.history.RoutesHistory;
import dagger.Module;
import dagger.Provides;

/**
 * Модулб для работы с кэш приложения
 *
 * @autor PRV
 */
@Module
class ApplicationCacheModule {

    private final Application application;

    ApplicationCacheModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    ObjectHistory<SearchTrain> getObjectHistory() {
        return new RoutesHistory(application);
    }
}
