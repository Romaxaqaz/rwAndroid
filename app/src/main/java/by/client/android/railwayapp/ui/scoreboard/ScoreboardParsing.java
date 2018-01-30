package by.client.android.railwayapp.ui.scoreboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BaseParsing;

/**
 * Класс дял парсинга поездов станции
 *
 * @author Roman Panteleev
 */
class ScoreboardParsing extends BaseParsing<Train, Stantion> {

    private static final String TRAIN_ID = "small[class=train_id]";
    private static final String PATH_ICO = "i[class]";
    private static final String PATH = "span[class=train_text]";
    private static final String TRAIN_TYPE = "div[class=train_description]";
    private static final String TIME_END = "b[class=train_end-time]";
    private static final String TIME_START = "b[class=train_start-time]";
    private static final String START_NUMBERING = "td[class=train_item train_halts train_number]";
    private static final String WAY = "td[class=train_item train_halts train_way]";
    private static final String PLATFORM = "td[class=train_item train_halts train_platform]";

    private static final String baseUrl = "http://rasp.rw.by/ru/tablo/?st_exp=%s";

    ScoreboardParsing(Stantion stantion) {
        super(stantion);
    }

    protected List<Train> pars() throws IOException {
        Document doc = Jsoup.connect(String.format(baseUrl, new StantionToCodeConverter().convert(getParam()))).get();

        List<Train> strList = new ArrayList<>();
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
            String trainNumber = checkEmpty(item.select(START_NUMBERING).first());

            String way = checkEmpty(item.select(WAY).first());
            String platform = checkEmpty(item.select(PLATFORM).first());

            Train train = Train.createBuilder()
                .setId(id)
                .setPath(path)
                .setTrainType(trainType)
                .setEnd(end)
                .setStart(start)
                .setNumbering(trainNumber)
                .setWay(way)
                .setPlatform(platform)
                .setPathType(ico);

            strList.add(train);
        }

        return strList;
    }
}
