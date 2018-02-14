package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.ScoreboardStantion;
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

    private ScoreboardStantion scoreboardStantion;
    private RailwayApi railwayApi;

    ScoreboardLoader(RailwayApi railwayApi, ScoreboardStantion scoreboardStantion) {
        this.railwayApi = railwayApi;
        this.scoreboardStantion = scoreboardStantion;
    }

    @Override
    public void load(RegisterLoader registerLoader) {
        new LoadScoreboardAsync(registerLoader).execute(scoreboardStantion);
    }

    private class LoadScoreboardAsync extends BaseLoader<ScoreboardStantion, List<Train>> {

        LoadScoreboardAsync(RegisterLoader registerLoader) {
            super(registerLoader);
        }

        @Override
        protected List<Train> call(ScoreboardStantion stantion) throws Exception {
            String stantionId = new StantionToCodeConverter().convert(stantion);
            String page = railwayApi.getScoreboardTable(stantionId).execute().body().string();
            return new ScoreboardParsing(page).pars();
        }
    }
}
