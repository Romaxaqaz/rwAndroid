package by.client.android.railwayapp.support;

/**
 * Интерфейс для реализации клиента загрузки данных
 *
 * @author ROMAN PANTELEEV
 */
public interface Client {

    /**
     * Загружает данные
     *
     * @param loader загрузчик данных
     */
    void load(Loader loader);
}
