package by.client.android.railwayapp.ui.traintimetable.history;

/**
 * Интерфейс для реализации кэша объектов
 *
 * @author PRV
 */
public interface ObjectCache<T> {

    /**
     * Сохраняет объект в кэш
     *
     * @param object объект для сохранения
     */
    void save(T object);

    /**
     * Получает объект их кэша
     *
     * @return возвращает объект из кэша
     */
    T read();
}
