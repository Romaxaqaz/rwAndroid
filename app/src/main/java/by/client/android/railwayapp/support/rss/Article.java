package by.client.android.railwayapp.support.rss;

import java.util.Date;

/**
 * Модель описания Rss item
 *
 * @author PRV
 */
public class Article {

    private String title;
    private String link;
    private Date pubDate;
    private String description;
    private String categories;

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLink() {
        return link;
    }

    public Article setLink(String link) {
        this.link = link;
        return this;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public Article setPubDate(Date pubDate) {
        this.pubDate = pubDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Article setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCategories() {
        return categories;
    }

    public Article setCategories(String categories) {
        this.categories = categories;
        return this;
    }
}
