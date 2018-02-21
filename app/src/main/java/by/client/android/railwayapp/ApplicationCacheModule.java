package by.client.android.railwayapp;

import java.util.List;

import javax.inject.Singleton;

import android.app.Application;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.traintimetable.history.Cache;
import by.client.android.railwayapp.ui.traintimetable.history.ObjectListHistory;
import by.client.android.railwayapp.ui.traintimetable.history.RoutesListHistory;
import dagger.Module;
import dagger.Provides;

/**
 * Модуль для работы с кэшем приложения
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
    ObjectListHistory<SearchTrain> getRouteHistory() {
        return new RoutesListHistory(new Cache<List<SearchTrain>>(application, "ROUTES_KEY"));
    }
}
