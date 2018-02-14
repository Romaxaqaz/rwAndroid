package by.client.android.railwayapp.ui.scoreboard;

import java.util.HashMap;
import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStation;

/**
 * Класс конвертер для преобразования типа {@link ScoreboardStation} в название станции
 *
 * @author ROMAN PANTELEEV
 */
class StationToNameConverter {

    private static final Map<ScoreboardStation, String> STATION_STRING_MAP = new HashMap<>();

    StationToNameConverter() {
        STATION_STRING_MAP.put(ScoreboardStation.MINSK, "Минск-Пассажирский");
        STATION_STRING_MAP.put(ScoreboardStation.ORSHA, "Орша-Центральная");
    }

    String convert(ScoreboardStation scoreboardStation) {
        return STATION_STRING_MAP.get(scoreboardStation);
    }
}
