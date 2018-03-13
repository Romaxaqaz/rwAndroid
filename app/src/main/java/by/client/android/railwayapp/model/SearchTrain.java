package by.client.android.railwayapp.model;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;
import by.client.android.railwayapp.api.rw.model.SearchStation;

/**
 * Модель запроса поездов
 *
 * @author ROMAN PANTELEEV
 */
public class SearchTrain implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.departureStation, flags);
        dest.writeParcelable(this.destinationStation, flags);
        dest.writeLong(ParcelableUtils.getDateTime(departureDate));
    }

    private SearchTrain(Parcel in) {
        this.departureStation = ParcelableUtils.readParcelable(in, SearchStation.class);
        this.destinationStation = ParcelableUtils.readParcelable(in, SearchStation.class);
        this.departureDate = ParcelableUtils.readDate(in);
    }

    public static final Parcelable.Creator<SearchTrain> CREATOR = new Parcelable.Creator<SearchTrain>() {
        @Override
        public SearchTrain createFromParcel(Parcel source) {
            return new SearchTrain(source);
        }

        @Override
        public SearchTrain[] newArray(int size) {
            return new SearchTrain[size];
        }
    };
}
