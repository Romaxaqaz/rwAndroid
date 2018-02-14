package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.ui.BaseLoader;

/**
 * Загрузчик данных для страницы {@link ScoreboardActivityFragment}
 *
 * @author ROMAN PANTELEEV
 */
class ScoreboardLoader implements Loader {

    private ScoreboardStation scoreboardStation;
    private RailwayApi railwayApi;

    ScoreboardLoader(RailwayApi railwayApi, ScoreboardStation scoreboardStation) {
        this.railwayApi = railwayApi;
        this.scoreboardStation = scoreboardStation;
    }

    @Override
    public void load(RegisterLoader registerLoader) {
        new LoadScoreboardAsync(registerLoader).execute(scoreboardStation);
    }

    private class LoadScoreboardAsync extends BaseLoader<ScoreboardStation, List<Train>> {

        LoadScoreboardAsync(RegisterLoader registerLoader) {
            super(registerLoader);
        }

        @Override
        protected List<Train> call(ScoreboardStation station) throws Exception {
            String stationId = new StationToCodeConverter().convert(station);
            String page = railwayApi.getScoreboardTable(stationId).execute().body().string();
            return new ScoreboardParsing(page).pars();
        }
    }
}
