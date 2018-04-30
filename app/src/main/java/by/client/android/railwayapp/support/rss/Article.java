package by.client.android.railwayapp.support.rss;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Модель описания Rss item
 *
 * @author PRV
 */
public class Article implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeLong(this.pubDate != null ? this.pubDate.getTime() : -1);
        dest.writeString(this.description);
        dest.writeString(this.categories);
    }

    Article() {
    }

    private Article(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        long tmpPubDate = in.readLong();
        this.pubDate = tmpPubDate == -1 ? null : new Date(tmpPubDate);
        this.description = in.readString();
        this.categories = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
