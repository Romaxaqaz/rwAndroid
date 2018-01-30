package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.Stantion;

/**
 * Конвертер для преобразования типа {@link Stantion} в код станции
 *
 * @author Roman Panteleev
 */
class StantionToCodeConverter {

    private static final Map<Stantion, String> STANTION_STRING_MAP = new HashMap<>();

    StantionToCodeConverter() {
        STANTION_STRING_MAP.put(Stantion.MINSK, "2100000");
    }

    String convert(Stantion stantion) {
        return STANTION_STRING_MAP.get(stantion);
    }
}
