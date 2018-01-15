package by.client.android.railwayapp.api;

/**
 * Интерфейс для реализации клиента для загрузки данных
 *
 * @author Roman Panteleev
 */
public interface Client {

    /**
     * Загружает данные
     *
     * @param loader загрузчик данных
     */
    void load(Loader loader);
}
