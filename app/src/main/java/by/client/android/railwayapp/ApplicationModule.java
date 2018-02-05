package by.client.android.railwayapp;

import android.app.Application;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.api.RailwayClient;
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
    SettingsService provideSettingService() {
        return new SettingsService(application);
    }

    @Provides
    Client getClient() {
        return new RailwayClient();
    }

    @Provides
    GlobalExceptionHandler getExceptionHandler() {
        return new GlobalExceptionHandler(application);
    }
}