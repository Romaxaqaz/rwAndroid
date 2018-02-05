package by.client.android.railwayapp.model.routetrain;

import java.io.Serializable;

/**
 * Модель для описания доступных мест поезда
 *
 * @author PRV
 */
public class Place implements Serializable {

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
}
