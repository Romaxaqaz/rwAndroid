package by.client.android.railwayapp.ui.base;

/**
 * Интерфес реализации ViewHolder для адаптеров
 *
 * @param <T> модель данных элемента списка
 *
 * @author Q-RPA
 */
public interface BaseHolder<T> {

    /**
     * Связывает модель данных с элементами на странице
     *
     * @param item элемент списка
     */
    void bind(T item);
}
