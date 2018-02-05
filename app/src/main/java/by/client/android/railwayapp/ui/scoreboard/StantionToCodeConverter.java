package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStantion;

/**
 * Конвертер для преобразования типа {@link ScoreboardStantion} в код станции
 *
 * @author ROMAN PANTELEEV
 */
class StantionToCodeConverter {

    private static final Map<ScoreboardStantion, String> STANTION_STRING_MAP = new HashMap<>();

    StantionToCodeConverter() {
        STANTION_STRING_MAP.put(ScoreboardStantion.MINSK, "2100000");
        STANTION_STRING_MAP.put(ScoreboardStantion.ORSHA, "2100000");
    }

    String convert(ScoreboardStantion scoreboardStantion) {
        return STANTION_STRING_MAP.get(scoreboardStantion);
    }
}
