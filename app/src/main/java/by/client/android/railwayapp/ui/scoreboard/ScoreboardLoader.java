package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.Loader;
import by.client.android.railwayapp.api.RegisterLoader;
import by.client.android.railwayapp.api.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.ScoreboardStantion;
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

    ScoreboardLoader(ScoreboardStantion scoreboardStantion, RegisterLoader registerLoader) {
        this.scoreboardStantion = scoreboardStantion;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadScoreboardAsync(registerLoader).execute(scoreboardStantion);
    }

    private static class LoadScoreboardAsync extends BaseAsyncTask<ScoreboardStantion, List<Train>> {

        private RegisterLoader registerLoader;

        LoadScoreboardAsync(RegisterLoader registerLoader) {
            this.registerLoader = registerLoader;
        }

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
            return new ScoreboardParsing(param[0]).pars();
        }
    }
}
