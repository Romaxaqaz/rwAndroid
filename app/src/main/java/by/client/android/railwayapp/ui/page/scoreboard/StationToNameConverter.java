package by.client.android.railwayapp.ui.page.scoreboard;

import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.support.common.MapBuilder;

/**
 * Класс конвертер для преобразования типа {@link ScoreboardStation} в название станции
 *
 * @author ROMAN PANTELEEV
 */
class StationToNameConverter {

    private static final Map<ScoreboardStation, String> STATION_STRING_MAP = new MapBuilder<ScoreboardStation, String>()
            .put(ScoreboardStation.MINSK, "Минск-Пассажирский")
            .put(ScoreboardStation.ORSHA, "Орша-Центральная")
            .put(ScoreboardStation.BREST, "Брест-Центральный")
            .build();

    String convert(ScoreboardStation scoreboardStation) {
        return STATION_STRING_MAP.get(scoreboardStation);
    }
}
