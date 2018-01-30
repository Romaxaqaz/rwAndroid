package by.client.android.railwayapp.ui.traintimetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainParameters;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.model.routetrain.TrainTime;
import by.client.android.railwayapp.ui.BaseParsing;

/**
 * Created by PanteleevRV on 19.01.2018.
 *
 * @author Roman Panteleev
 */
class TrainTimeParsing extends BaseParsing<TrainRoute, SearchTrain> {

    private static final String TRAIN_ID = "small[class=train_id]";
    private static final String PATH_ICO = "i[class]";
    private static final String PATH = "a[class=train_text]";
    private static final String TRAIN_TYPE = "div[class=train_description]";
    private static final String TIME_END = "b[class=train_end-time]";
    private static final String TIME_START = "b[class=train_start-time]";

    private static final String ELECTRONIC_REGISTRATION = "i[class=b-spec spec_reserved]";
    private static final String FAVOURITE_TRAIN = "i[class=b-spec spec_comfort]";
    private static final String FAST_TRAIN = "i[class=b-spec spec_speed]";

    private static final String TRAVEL_TIME = "span[class=train_time-total]";

    TrainTimeParsing(SearchTrain searchTrain) {
        super(searchTrain);
    }

    @Override
    public List<TrainRoute> pars() throws Exception {
        Document doc = Jsoup.connect("http://rasp.rw.by/ru/route/")
            .data("from", "Минск-Пассажирский")
            .data("to", "Орша-Центральная")
            .data("date", "2018-02-12")
            .data("from_exp", "0")
            .data("from_esr", "0")
            .data("to_exp", "2100170")
            .data("to_esr", "166403")
            .get();

        List<TrainRoute> strList = new ArrayList<>();
        Elements names = doc.select(TABLE).first().select(TR);

        for (Element item : names) {
            String id = checkEmpty(item.select(TRAIN_ID).first());
            if (Objects.equals(id, EMPTY_STRING)) {
                continue;
            }

            String ico = item.select(PATH_ICO).first().getAllElements().attr("class");
            String path = checkEmpty(item.select(PATH).first());
            String trainType = checkEmpty(item.select(TRAIN_TYPE).first());

            String start = checkEmpty(item.select(TIME_END).first());
            String end = checkEmpty(item.select(TIME_START).first());
            String travelTime = checkEmpty(item.select(TRAVEL_TIME).first());

            Boolean electronic = isValue(item.select(ELECTRONIC_REGISTRATION).first());
            Boolean favourite = isValue(item.select(FAVOURITE_TRAIN).first());
            Boolean fast = isValue(item.select(FAST_TRAIN).first());

            List<Place> places = new ArrayList<>();
            Elements elementPlace = item.select("ul[class=train_details-group]");
            for (Element place : elementPlace) {

                String name = place.getElementsByClass("train_note").text();
                String cost = place.getElementsByClass("denom_after").text();

                Element countPlace = place.getElementsByClass("train_seats lnk").first();
                String count = countPlace.text();
                String link = countPlace.attr("data-get");

                places.add(new Place(name, count, link, cost));
            }

            TrainRoute trainRoute = TrainRoute.createBuilder()
                .setId(id)
                .setIco(ico)
                .setPath(path)
                .setTrainType(trainType)
                .setTrainTime(new TrainTime(start, end))
                .setTravelTime(travelTime)
                .setParameter(new TrainParameters(electronic, favourite, fast))
                .setPlaces(places);

            strList.add(trainRoute);
        }

        return strList;
    }

    private boolean isValue(Element element) {
        return element != null;
    }
}
