package by.client.android.railwayapp;

import android.app.Application;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.api.RailwayClient;
import by.client.android.railwayapp.ui.settings.SettingsService;

/**
 * Created by PanteleevRV on 15.01.2018.
 *
 * @author Roman Panteleev
 */
public class AndroidApplication extends Application {

    private Client client;
    private SettingsService settingsService;

    @Override
    public void onCreate() {
        super.onCreate();

        client = new RailwayClient();
        settingsService = new SettingsService(this);
    }

    /**
     * Класс для работы с настройками приложения.
     */
    public SettingsService getSettingsService() {
        return settingsService;
    }


    /**
     * Класс для загрузки данных с сервера
     */
    public Client getClient() {
        return client;
    }
}
