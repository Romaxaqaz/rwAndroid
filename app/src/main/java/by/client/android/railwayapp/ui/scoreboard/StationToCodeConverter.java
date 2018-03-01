package by.client.android.railwayapp.ui.scoreboard;

import java.util.Map;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.support.common.MapBuilder;

/**
 * Конвертер для преобразования типа {@link ScoreboardStation} в код станции
 *
 * @author ROMAN PANTELEEV
 */
class StationToCodeConverter {

    private static final Map<ScoreboardStation, String> STATION_STRING_MAP = new MapBuilder<ScoreboardStation, String>()
        .put(ScoreboardStation.MINSK, "2100000")
        .put(ScoreboardStation.ORSHA, "2100000")
        .build();

    String convert(ScoreboardStation scoreboardStation) {
        return STATION_STRING_MAP.get(scoreboardStation);
    }
}
