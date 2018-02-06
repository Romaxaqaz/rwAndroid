package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.support.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.ScoreboardStantion;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BaseAsyncTask;

/**
 * Загрузчик данных для страницы {@link ScoreboardActivityFragment}
 *
 * @author ROMAN PANTELEEV
 */
class ScoreboardLoader implements Loader {

    private ScoreboardStantion scoreboardStantion;
    private RegisterLoader registerLoader;
    private RailwayApi railwayApi;

    ScoreboardLoader(RailwayApi railwayApi, ScoreboardStantion scoreboardStantion, RegisterLoader registerLoader) {
        this.railwayApi = railwayApi;
        this.scoreboardStantion = scoreboardStantion;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadScoreboardAsync().execute(scoreboardStantion);
    }

    private class LoadScoreboardAsync extends BaseAsyncTask<ScoreboardStantion, List<Train>> {

        @Override
        protected void onStart() {
            registerLoader.onStart();
        }

        @Override
        protected void onCompleted(List<Train> trains) {
            registerLoader.onSuccess(trains);
        }

        @Override
        protected void onFinish(boolean successful) {
            registerLoader.onFinish(successful);
        }

        @Override
        protected void onError(Exception exception) {
            registerLoader.onError(exception);
        }

        @Override
        protected List<Train> runTask(ScoreboardStantion... param) throws Exception {
            String stantion = new StantionToCodeConverter().convert(scoreboardStantion);
            String page = railwayApi.getScoreboardTable(stantion).execute().body().string();
            return new ScoreboardParsing(page).pars();
        }
    }
}
