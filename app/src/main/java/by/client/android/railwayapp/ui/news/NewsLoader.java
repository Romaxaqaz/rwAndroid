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
final class NewsLoader implements Loader {

    private static final String URL = "http://www.rw.by/rss/";

    @Override
    public void load(RegisterLoader registerLoader) {
        new LoadNewsAsync(registerLoader).execute(URL);
    }

    private class LoadNewsAsync extends BaseLoader<String, List<Article>> {

        LoadNewsAsync(RegisterLoader registerLoader) {
            super(registerLoader);
        }

        @Override
        protected List<Article> call(String url) throws Exception {
            return new RssParser().parseRss(url);
        }
    }
}
