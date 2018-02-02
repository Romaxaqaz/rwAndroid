package by.client.android.railwayapp.model;

import java.io.Serializable;
import java.util.Date;

import by.client.android.railwayapp.api.rw.model.SearchStantion;

/**
 * Created by PanteleevRV on 18.01.2018.
 *
 * @author ROMAN PANTELEEV
 */
public class SearchTrain implements Serializable {

    /**
     * Откуда
     */
    private SearchStantion arrive;

    /**
     * Куда
     */
    private SearchStantion arrival;

    /**
     * Когда
     */
    private Date when;

    public SearchTrain(SearchStantion arrive, SearchStantion arrival, Date when) {
        this.arrive = arrive;
        this.arrival = arrival;
        this.when = when;
    }

    public SearchStantion getArrive() {
        return arrive;
    }

    public SearchStantion getArrival() {
        return arrival;
    }

    public Date getWhen() {
        return when;
    }
}
