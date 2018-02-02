package by.client.android.railwayapp.model.routetrain;

import java.io.Serializable;

/**
 * Created by PanteleevRV on 19.01.2018.
 */

public class Place implements Serializable {

    private String name;

    private String freePlaces;

    private String link;

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
}
