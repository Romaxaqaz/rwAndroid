package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.Train;

/**
 * Загрузчик данных для страницы {@link ScoreboardActivityFragment}
 *
 * @author ROMAN PANTELEEV
 */
public class ScoreboardLoader {

    private ScoreboardStation scoreboardStation;
    private RailwayApi railwayApi;

    ScoreboardLoader(RailwayApi railwayApi, ScoreboardStation scoreboardStation) {
        this.railwayApi = railwayApi;
        this.scoreboardStation = scoreboardStation;
    }

    public List<Train> load() throws Exception {
        String stationId = new StationToCodeConverter().convert(scoreboardStation);
        String page = railwayApi.getScoreboardTable(stationId).execute().body().string();
        return new ScoreboardParsing(page).pars();
    }
}
