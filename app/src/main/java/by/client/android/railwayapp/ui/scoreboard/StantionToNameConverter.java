package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStantion;

/**
 * Класс конвертер для преобразования типа {@link ScoreboardStantion} в название станции
 *
 * @author ROMAN PANTELEEV
 */
class StantionToNameConverter {

    private static final Map<ScoreboardStantion, String> STANTION_STRING_MAP = new HashMap<>();

    StantionToNameConverter() {
        STANTION_STRING_MAP.put(ScoreboardStantion.MINSK, "Минск-Пассажирский");
        STANTION_STRING_MAP.put(ScoreboardStantion.ORSHA, "Орша-Центральная");
    }

    String convert(ScoreboardStantion scoreboardStantion) {
        return STANTION_STRING_MAP.get(scoreboardStantion);
    }
}
