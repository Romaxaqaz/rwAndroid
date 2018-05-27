package by.client.android.railwayapp.ui.page.trainroute;

import java.util.List;

import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.RouteItem;

class TrainRouteLoader {

    private static final String DEFAULT_DATE = "everyday";

    private RailwayApi railwayApi;
    private String trainNumber;

    TrainRouteLoader(RailwayApi railwayApi, String trainNumber) {
        this.railwayApi = railwayApi;
        this.trainNumber = trainNumber;
    }

    public List<RouteItem> load() throws Exception {
        String page = railwayApi.getTrainRoutePage(trainNumber, DEFAULT_DATE).execute().body().string();
        return new TrainRouteParsing(page).pars();
    }
}
