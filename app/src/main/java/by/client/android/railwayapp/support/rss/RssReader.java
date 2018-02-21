package by.client.android.railwayapp.support.rss;

import java.util.List;

/**
 * Интрфейс для реализации RSS ридеров
 *
 * @author PRV
 */
public interface RssReader<T> {

    List<T> parseRss(String url);
}
