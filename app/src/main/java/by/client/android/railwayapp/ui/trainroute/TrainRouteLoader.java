package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.support.SafeRegistrationSendListener;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.ui.BaseAsyncTask;

class TrainRouteLoader implements Loader {

    private static final String DEFAULT_DATE = "everyday";

    private RailwayApi railwayApi;
    private String trainNumber;
    private RegisterLoader registerLoader;

    TrainRouteLoader(RailwayApi railwayApi, String trainNumber, RegisterLoader registerLoader) {
        this.railwayApi = railwayApi;
        this.trainNumber = trainNumber;
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    public void load() {
        new LoadTrainRouteAsync(registerLoader).execute(trainNumber);
    }

    private class LoadTrainRouteAsync extends BaseAsyncTask<String, List<RouteItem>> {

        private RegisterLoader registerLoader;

        LoadTrainRouteAsync(RegisterLoader registerLoader) {
            this.registerLoader = registerLoader;
        }

        @Override
        protected void onStart() {
            registerLoader.onStart();
        }

        @Override
        protected void onCompleted(List<RouteItem> trains) {
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
        protected List<RouteItem> runTask(String... param) throws Exception {
            String page = railwayApi.getTrainRoutePage(trainNumber, DEFAULT_DATE).execute().body().string();
            return new TrainRouteParsing(page).pars();
        }
    }
}
