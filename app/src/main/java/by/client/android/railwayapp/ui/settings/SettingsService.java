package by.client.android.railwayapp.ui.settings;

import android.content.Context;
import by.client.android.railwayapp.api.ScoreboardStantion;
import by.client.android.railwayapp.model.Settings;

/**
 * Класс для работы с настройками приложения.
 *
 * @author Q-RPA
 */
public class SettingsService {

    private static final String URL_KEY = "BASE_URL_KEY";

    private static final String SCOREBOARD_STANTION_KEY = "SCOREBOARD_STANTION_KEY";

    private SettingSharedPreferences sharedPreferences;
    private ApplicationProperties applicationProperties;
    private Settings settings;

    public SettingsService(Context context) {
        applicationProperties = new ApplicationProperties(context);
        sharedPreferences = new SettingSharedPreferences(context);

        initParams();
    }

    private void initParams() {
        String url = sharedPreferences.getValue(URL_KEY, applicationProperties.get(URL_KEY));

        settings = new Settings(url);
    }

    /**
     * Сохраняет настройки приложения.
     *
     * @param settings модель настроек {@link Settings}.
     */
    public void saveSettings(Settings settings) {
        this.settings = settings;

        sharedPreferences.setValue(URL_KEY, settings.getUrl());
    }

    /**
     * Получает настройки приложения {@link Settings}.
     *
     * @return возвращает модель настроек приложения.
     */
    public Settings getSettings() {
        return settings;
    }

    public void saveScoreboardStantion(ScoreboardStantion stantion) {
        sharedPreferences.setValue(SCOREBOARD_STANTION_KEY, String.valueOf(stantion));
    }

    public ScoreboardStantion getScoreboardStantion() {
        String stantion = sharedPreferences.getValue(SCOREBOARD_STANTION_KEY, String.valueOf(ScoreboardStantion.MINSK));
        return ScoreboardStantion.valueOf(stantion);
    }
}
