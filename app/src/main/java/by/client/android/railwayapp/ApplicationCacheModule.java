package by.client.android.railwayapp;

import android.app.Application;

import javax.inject.Singleton;

import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.support.database.DataBase;
import by.client.android.railwayapp.ui.page.traintimetable.history.TrainHistoryDataBase;
import by.client.android.railwayapp.ui.page.traintimetable.history.TrainHistoryDbHelper;
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
        return new TrainHistoryDataBase(new TrainHistoryDbHelper(application));
    }
}
