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

    private static final String baseUrl = "http://rasp.rw.by/ru/train/?train=%s&date=everyday";

    private static final String STANTION = "a[class=train_name -map train_text]";
    private static final String ARRIVAL = "b[class=train_end-time]";
    private static final String ARRIVED = "b[class=train_start-time]";
    private static final String TRAVEL_TIME = "span[class=train_time-total]";
    private static final String STAY = "b[class=train_stop-time]";

    TrainRouteParsing(String trainNumber) {
        super(trainNumber);
    }

    protected List<RouteItem> pars() throws Exception {
        Document doc = Jsoup.connect(String.format(baseUrl, getParam())).get();

        List<RouteItem> strList = new ArrayList<>();
        Element table = doc.select(TABLE).first();
        if (table == null) {
            throw new ParsingException("Table for parsing data not found");
        }

        Elements names = table.select(TR);
        for (Element item : names) {
            String stantion = checkEmpty(item.select(STANTION).first());
            if (stantion == EMPTY_STRING) {
                continue;
            }

            String arrival = checkEmpty(item.select(ARRIVAL).first());
            String arrived = checkEmpty(item.select(ARRIVED).first());
            String travelTime = checkEmpty(item.select(TRAVEL_TIME).first());
            String stay = checkEmpty(item.select(STAY).first());

            RouteItem train = RouteItem.createBuilder()
                .setStantion(stantion)
                .setArrival(arrival)
                .setArrived(arrived)
                .setTravelTime(travelTime)
                .setStay(stay);

            strList.add(train);
        }

        return strList;
    }
}
