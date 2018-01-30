package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.Stantion;

/**
 * Класс конвертер для преобразования типа {@link Stantion} в название станции
 *
 * @author Roman PAnteleev
 */
class StantionToNameConverter {

    private static final Map<Stantion, String> STANTION_STRING_MAP = new HashMap<>();

    StantionToNameConverter() {
        STANTION_STRING_MAP.put(Stantion.MINSK, "Минск-Пассажирский");
    }

    String convert(Stantion stantion) {
        return STANTION_STRING_MAP.get(stantion);
    }
}
