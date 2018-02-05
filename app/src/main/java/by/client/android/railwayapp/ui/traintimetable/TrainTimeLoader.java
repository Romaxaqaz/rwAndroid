package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import by.client.android.railwayapp.api.Loader;
import by.client.android.railwayapp.api.RegisterLoader;
import by.client.android.railwayapp.api.SafeRegistrationSendListener;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.BaseAsyncTask;

/**
 * Загрузчик поездов по заданному маршруту
 *
 * @author ROMAN PANTELEEV
 */
class TrainTimeLoader implements Loader {

    private SearchTrain searchTrain;
    private RegisterLoader registerLoader;

    TrainTimeLoader(SearchTrain searchTrain, RegisterLoader registerLoader) {
        this.searchTrain = searchTrain;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadTrainTimeAsync(registerLoader).execute(searchTrain);
    }

    private static class LoadTrainTimeAsync extends BaseAsyncTask<SearchTrain, List<TrainRoute>> {

        private RegisterLoader registerLoader;

        LoadTrainTimeAsync(RegisterLoader registerLoader) {
            this.registerLoader = registerLoader;
        }

        @Override
        protected void onStart() {
            registerLoader.onStart();
        }

        @Override
        protected void onCompleted(List<TrainRoute> trains) {
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
        protected List<TrainRoute> runTask(SearchTrain... param) throws Exception {
            return new TrainTimeParsing(param[0]).pars();
        }
    }
}
