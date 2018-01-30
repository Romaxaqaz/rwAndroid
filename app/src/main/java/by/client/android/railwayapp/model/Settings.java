package by.client.android.railwayapp.model;

/**
 * Модель настроек приложения
 *
 * @author Q-RPA
 */
public class Settings {

    /**
     * Адрес сервера
     */
    private String url;

    public Settings(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
