package by.client.android.railwayapp.support;

/**
 * Интерфейс для реализации клиента загрузки данных
 *
 * @author ROMAN PANTELEEV
 */
public interface Client {

    /**
     * Выполняет запрос с помощью загрузчика
     *
     * @param loader загрузчик данных
     * @param registerLoader callback загрузки данных
     */
    void send(Loader loader, RegisterLoader registerLoader);
}
