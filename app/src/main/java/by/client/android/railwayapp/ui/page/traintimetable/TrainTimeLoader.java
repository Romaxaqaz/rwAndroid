package by.client.android.railwayapp.ui.page.traintimetable;

import java.util.List;

import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;

/**
 * Загрузчик поездов по заданному маршруту
 *
 * @author ROMAN PANTELEEV
 */
class TrainTimeLoader {

    private RailwayApi railwayApi;
    private SearchTrain searchTrain;

    TrainTimeLoader(RailwayApi railwayApi, SearchTrain searchTrain) {
        this.railwayApi = railwayApi;
        this.searchTrain = searchTrain;
    }

    public List<TrainRoute> load() throws Exception {
        String page = railwayApi.getTrainRoutes(
                searchTrain.getDestinationStation().getValue(),
                searchTrain.getDepartureStation().getValue(),
                new DateToStringConverter().convert(searchTrain.getDepartureDate()),
                searchTrain.getDepartureStation().getExp(),
                searchTrain.getDepartureStation().getEcp(),
                searchTrain.getDestinationStation().getExp(),
                searchTrain.getDestinationStation().getEcp()).execute().body().string();

        return new TrainTimeParsing(page).pars();
    }
}
