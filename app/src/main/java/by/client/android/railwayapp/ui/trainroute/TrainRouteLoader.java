package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.ui.BaseLoader;

class TrainRouteLoader implements Loader {

    private static final String DEFAULT_DATE = "everyday";

    private RailwayApi railwayApi;
    private String trainNumber;

    TrainRouteLoader(RailwayApi railwayApi, String trainNumber) {
        this.railwayApi = railwayApi;
        this.trainNumber = trainNumber;
    }

    @Override
    public void load(RegisterLoader registerLoader) {
        new LoadTrainRouteAsync(registerLoader).execute(trainNumber);
    }

    private class LoadTrainRouteAsync extends BaseLoader<String, List<RouteItem>> {

        LoadTrainRouteAsync(RegisterLoader registerLoader) {
            super(registerLoader);
        }

        @Override
        protected List<RouteItem> call(String param) throws Exception {
            String page = railwayApi.getTrainRoutePage(param, DEFAULT_DATE).execute().body().string();
            return new TrainRouteParsing(page).pars();
        }
    }
}
