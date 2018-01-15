package by.client.android.railwayapp.ui.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;

/**
 * Класс дял парсинга поездов станции
 *
 * @author Roman Panteleev
 */
class ScoreboardParsing {

    private static final String TABLE = "table";
    private static final String TR = "tr";
    private static final String TD = "td";

    private static final String baseUrl = "http://rasp.rw.by/ru/tablo/?st_exp=%s";
    private Stantion stantion;

    ScoreboardParsing(Stantion stantion) {
        this.stantion = stantion;
    }

    //TODO тестовая реализация
    List<Train> load() throws Exception {
        Document doc = Jsoup.connect(String.format(baseUrl, new StantionToCodeConverter().convert(stantion))).get();

        List<Train> strList = new ArrayList<>();
        Element table = doc.select(TABLE).get(0);
        Elements names = table.select(TR);

        for (Element item : names) {
            Elements tds = item.select(TD);
            if (tds.isEmpty()) {
                continue;
            }

            strList.add(new Train(getString(tds, 0), getString(tds, 1), getString(tds, 2), getString(tds, 3),
                getString(tds, 4), getString(tds, 5)));
        }

        return strList;
    }

    private String getString(Elements element, int index) {
        return element.get(index).text() + "\n";
    }
}
