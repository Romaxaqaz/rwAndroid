package by.client.android.railwayapp.model;

import java.util.Date;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.support.database.room.DateConverters;

/**
 * Модель запроса поездов
 *
 * @author ROMAN PANTELEEV
 */
@Entity
public class SearchTrain implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    /**
     * Станция отправления
     */
    @Embedded(prefix = "departure_")
    private SearchStation departureStation;

    /**
     * Станция назначения
     */
    @Embedded(prefix = "destination_")
    private SearchStation destinationStation;

    /**
     * Дата отправления
     */
    @TypeConverters({DateConverters.class})
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
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
