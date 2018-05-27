package by.client.android.railwayapp;

import android.app.Application;

import javax.inject.Singleton;

import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.api.rw.RailwayRetrofitService;
import by.client.android.railwayapp.ui.page.settings.SettingsService;
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
    GlobalExceptionHandler getExceptionHandler() {
        return new GlobalExceptionHandler(application);
    }

    @Provides
    @Singleton
    RailwayApi getRailwayApi() {
        return new RailwayRetrofitService().getRailwayApi();
    }
}