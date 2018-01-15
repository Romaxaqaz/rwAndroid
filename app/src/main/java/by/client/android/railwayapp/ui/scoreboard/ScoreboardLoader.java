package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.Loader;
import by.client.android.railwayapp.api.RegisterLoader;
import by.client.android.railwayapp.api.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BaseAsyncTask;

/**
 * Загрузчик данных для страницы {@link ScoreboardActivity}
 *
 * @author Roman Panteleev
 */
class ScoreboardLoader implements Loader {

    private Stantion stantion;
    private RegisterLoader registerLoader;

    ScoreboardLoader(RegisterLoader registerLoader, Stantion stantion) {
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
        this.stantion = stantion;
    }

    @Override
    public void load() {
        new LoadScoreboardAsync().execute();
    }

    private class LoadScoreboardAsync extends BaseAsyncTask<Void, List<Train>> {

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
        protected List<Train> runTask(Void... param) throws Exception {
            return new ScoreboardParsing(stantion).load();
        }
    }
}
