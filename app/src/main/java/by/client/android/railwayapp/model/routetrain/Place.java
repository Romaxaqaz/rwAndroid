package by.client.android.railwayapp.model.routetrain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Модель для описания доступных мест поезда
 *
 * @author PRV
 */
public class Place implements Parcelable {

    /**
     * Тип места
     * <p>Плацкарт, общий и т.д.</p>
     */
    private String name;

    /**
     * Количество свободных мест
     */
    private String freePlaces;

    /**
     * Сслыка для запроса подробной информации по магону
     */
    private String link;

    /**
     * Стоимоть места
     */
    private String price;

    public Place(String name, String freePlaces, String link, String price) {
        this.name = name;
        this.freePlaces = freePlaces;
        this.link = link;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(String freePlaces) {
        this.freePlaces = freePlaces;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.freePlaces);
        dest.writeString(this.link);
        dest.writeString(this.price);
    }

    private Place(Parcel in) {
        this.name = in.readString();
        this.freePlaces = in.readString();
        this.link = in.readString();
        this.price = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
