package by.client.android.railwayapp;

import javax.inject.Singleton;

import android.app.Application;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.support.RailwayClient;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.api.rw.RailwayService;
import by.client.android.railwayapp.ui.settings.SettingsService;
import dagger.Module;
import dagger.Provides;

/**
 * Основной модуль приложения
 *
 * @author PRV
 */
@Module
class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SettingsService provideSettingService() {
        return new SettingsService(application);
    }

    @Provides
    @Singleton
    Client getClient() {
        return new RailwayClient();
    }

    @Provides
    @Singleton
    GlobalExceptionHandler getExceptionHandler() {
        return new GlobalExceptionHandler(application);
    }

    @Provides
    @Singleton
    RailwayApi getRailwayApi() {
        return new RailwayService().getRailwayApi();
    }
}