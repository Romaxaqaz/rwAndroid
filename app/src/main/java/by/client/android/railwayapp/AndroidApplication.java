package by.client.android.railwayapp;

import javax.inject.Inject;

import android.app.Application;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.ui.settings.SettingsService;

/**
 * Created by PanteleevRV on 15.01.2018.
 *
 * @author ROMAN PANTELEEV
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static AndroidApplication app;

    @Inject
    SettingsService settingsService;

    @Inject
    Client client;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        getApplicationComponent().inject(this);
    }

    /**
     * Класс для загрузки данных с сервера
     */
    public Client getClient() {
        return client;
    }

    public static AndroidApplication getApp() {
        return app;
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        }
        return applicationComponent;
    }
}
