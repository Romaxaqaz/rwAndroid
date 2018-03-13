package by.client.android.railwayapp;

import javax.inject.Singleton;

import android.app.Application;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.support.database.DataBase;
import by.client.android.railwayapp.ui.traintimetable.history.TrainHistoryDataBase;
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
    DataBase<SearchTrain> getTrainHistoryDb() {
        return new TrainHistoryDataBase(application);
    }
}
