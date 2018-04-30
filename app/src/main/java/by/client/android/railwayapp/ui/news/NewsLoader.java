package by.client.android.railwayapp.ui.news;

import java.util.List;

import by.client.android.railwayapp.support.Loader;
import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.support.rss.Article;
import by.client.android.railwayapp.support.rss.RssParser;
import by.client.android.railwayapp.ui.BaseLoader;

/**
 * Created by PanteleevRV on 21.02.2018.
 *
 * @author PRV
 */
final class NewsLoader {

    private static final String URL = "https://www.rw.by/rss/";

    public List<Article> load() {
        return new RssParser().parseRss(URL);
    }
}
