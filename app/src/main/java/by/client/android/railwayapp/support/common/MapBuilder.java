package by.client.android.railwayapp.support.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Билдер для упрощения построения {@link Map} на этапе объявления
 *
 * <pre>
 *     Пример использования:
 *
 *   private static final Map<Integer, String> TEST_MAP = new MapBuilder<Integer, String>()
 *                   .put(0, "0")
 *                   .build();
 * </pre>
 *
 * @author PRV
 */
public class MapBuilder<K, V> {

    private Map<K, V> map;

    public MapBuilder() {
        this.map = new HashMap<K, V>();
    }

    /**
     * Помещает значение в {@link Map}
     *
     * @param key ключ параметра
     * @param value значение параметра
     */
    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    /**
     * Выполняет построение {@link Map} путем возвращения сформированного словаря
     */
    public Map<K, V> build() {
        return map;
    }
}
