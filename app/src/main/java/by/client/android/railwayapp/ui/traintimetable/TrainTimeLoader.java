package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.support.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.BaseAsyncTask;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;

/**
 * Загрузчик поездов по заданному маршруту
 *
 * @author ROMAN PANTELEEV
 */
class TrainTimeLoader implements Loader {

    private RailwayApi railwayApi;
    private SearchTrain searchTrain;
    private RegisterLoader registerLoader;

    TrainTimeLoader(RailwayApi railwayApi, SearchTrain searchTrain, RegisterLoader registerLoader) {
        this.railwayApi = railwayApi;
        this.searchTrain = searchTrain;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadTrainTimeAsync().execute(searchTrain);
    }

    private class LoadTrainTimeAsync extends BaseAsyncTask<SearchTrain, List<TrainRoute>> {

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

            String page = railwayApi.getTrainRoutes(
                searchTrain.getDestinationStantion().getValue(),
                searchTrain.getDepartureStation().getValue(),
                new DateToStringConverter().convert(searchTrain.getDepartureDate()),
                searchTrain.getDepartureStation().getExp(),
                searchTrain.getDepartureStation().getEcp(),
                searchTrain.getDestinationStantion().getExp(),
                searchTrain.getDestinationStantion().getEcp()).execute().body().string();

            return new TrainTimeParsing(page).pars();
        }
    }
}
