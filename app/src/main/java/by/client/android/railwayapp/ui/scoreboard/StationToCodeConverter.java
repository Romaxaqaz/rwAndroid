package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStation;

/**
 * Конвертер для преобразования типа {@link ScoreboardStation} в код станции
 *
 * @author ROMAN PANTELEEV
 */
class StationToCodeConverter {

    private static final Map<ScoreboardStation, String> STATION_STRING_MAP = new HashMap<>();

    StationToCodeConverter() {
        STATION_STRING_MAP.put(ScoreboardStation.MINSK, "2100000");
        STATION_STRING_MAP.put(ScoreboardStation.ORSHA, "2100000");
    }

    String convert(ScoreboardStation scoreboardStation) {
        return STATION_STRING_MAP.get(scoreboardStation);
    }
}
