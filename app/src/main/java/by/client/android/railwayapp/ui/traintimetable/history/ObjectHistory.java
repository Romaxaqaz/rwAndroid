package by.client.android.railwayapp.ui.traintimetable.history;

import java.util.List;

/**
 * Интерфейс для реализации хранилища объектоа
 *
 * @autor PRV
 */
public interface ObjectHistory<T> {

    /**
     * Добавляет объект в историюю
     *
     * @param item объект для добавления в историюю
     */
    void add(T item);

    /**
     * Возвращает список всех сохраненных объектов
     */
    List<T> getAll();

    /**
     * Очищает список истории
     */
    void clear();
}
