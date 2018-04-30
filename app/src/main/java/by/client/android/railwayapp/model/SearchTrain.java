package by.client.android.railwayapp.model;

import java.io.Serializable;
import java.util.Date;

import by.client.android.railwayapp.api.rw.model.SearchStation;

/**
 * Модель запроса поездов
 *
 * @author ROMAN PANTELEEV
 */
public class SearchTrain implements Serializable {

    /**
     * Станция отправления
     */
    private SearchStation departureStation;

    /**
     * Станция назначения
     */
    private SearchStation destinationStation;

    /**
     * Дата отправления
     */
    private Date departureDate;

    public SearchTrain(SearchStation departureStation, SearchStation destinationStation, Date departureDate) {
        this.departureStation = departureStation;
        this.destinationStation = destinationStation;
        this.departureDate = departureDate;
    }

    public SearchStation getDepartureStation() {
        return departureStation;
    }

    public SearchStation getDestinationStation() {
        return destinationStation;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SearchTrain that = (SearchTrain) o;
        return departureStation.equals(that.departureStation) && destinationStation.equals(that.destinationStation);
    }

    @Override
    public int hashCode() {
        int result = departureStation.hashCode();
        result = 31 * result + destinationStation.hashCode();
        return result;
    }
}
