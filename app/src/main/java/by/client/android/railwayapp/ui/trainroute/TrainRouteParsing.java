package by.client.android.railwayapp.ui.trainroute;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.ui.BaseParsing;
import by.client.android.railwayapp.ui.ParsingException;

class TrainRouteParsing extends BaseParsing<RouteItem, String> {

    private static final String STATION = "a[class=train_name -map train_text]";
    private static final String ARRIVAL = "b[class=train_end-time]";
    private static final String ARRIVED = "b[class=train_start-time]";
    private static final String TRAVEL_TIME = "span[class=train_time-total]";
    private static final String STAY = "b[class=train_stop-time]";

    TrainRouteParsing(String page) {
        super(page);
    }

    protected List<RouteItem> pars() throws Exception {
        Document doc = Jsoup.parse(getParam());

        List<RouteItem> routeItemList = new ArrayList<>();
        Element table = doc.select(TABLE).first();
        if (table == null) {
            throw new ParsingException("Table for parsing data not found");
        }

        Elements rows = table.select(TR);
        for (Element item : rows) {
            String station = checkEmpty(item.select(STATION).first());
            if (station == EMPTY_STRING) {
                continue;
            }

            String arrival = checkEmpty(item.select(ARRIVAL).first());
            String arrived = checkEmpty(item.select(ARRIVED).first());
            String travelTime = checkEmpty(item.select(TRAVEL_TIME).first());
            String stay = checkEmpty(item.select(STAY).first());

            RouteItem train = RouteItem.createBuilder()
                .setStation(station)
                .setArrival(arrival)
                .setArrived(arrived)
                .setTravelTime(travelTime)
                .setStay(stay);

            routeItemList.add(train);
        }

        return routeItemList;
    }
}
