package by.client.android.railwayapp.model.routetrain;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import by.client.android.railwayapp.model.ParcelableUtils;

/**
 * Модель описания поезда
 *
 * @author ROMAN PANTELEEV
 */
public class TrainRoute implements Parcelable {

    /**
     * Номер поезда
     */
    private String id;

    /**
     * Иконка поезда
     */
    private String ico;

    /**
     * Маршрут
     */
    private String path;

    /**
     * Тип поезда
     */
    private String trainType;

    /**
     * Время прибытия и отправления
     */
    private TrainTime trainTime;

    /**
     * Время в пути
     */
    private String travelTime;

    /**
     * Параметры поезда
     */
    private TrainParameters trainParameters;

    /**
     * Свободные мемста
     */
    private List<Place> places;

    private TrainRoute() {

    }

    public static TrainRoute createBuilder() {
        return new TrainRoute();
    }


    public TrainRoute setId(String id) {
        this.id = id;
        return this;
    }

    public TrainRoute setIco(String ico) {
        this.ico = ico;
        return this;
    }

    public TrainRoute setPath(String path) {
        this.path = path;
        return this;
    }

    public TrainRoute setTrainType(String trainType) {
        this.trainType = trainType;
        return this;
    }

    public TrainRoute setTrainTime(TrainTime trainTime) {
        this.trainTime = trainTime;
        return this;
    }

    public TrainRoute setTravelTime(String travelTime) {
        this.travelTime = travelTime;
        return this;
    }

    public TrainRoute setPlaces(List<Place> places) {
        this.places = places;
        return this;
    }

    public TrainRoute setParameter(TrainParameters trainParameters) {
        this.trainParameters = trainParameters;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getIco() {
        return ico;
    }

    public String getPath() {
        return path;
    }

    public String getTrainType() {
        return trainType;
    }

    public TrainTime getTrainTime() {
        return trainTime;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public TrainParameters getParameters() {
        return trainParameters;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ico);
        dest.writeString(this.path);
        dest.writeString(this.trainType);
        dest.writeParcelable(this.trainTime, flags);
        dest.writeString(this.travelTime);
        dest.writeParcelable(this.trainParameters, flags);
        dest.writeTypedList(this.places);
    }

    private TrainRoute(Parcel in) {
        this.id = in.readString();
        this.ico = in.readString();
        this.path = in.readString();
        this.trainType = in.readString();
        this.trainTime = ParcelableUtils.readParcelable(in, TrainTime.class);
        this.travelTime = in.readString();
        this.trainParameters = ParcelableUtils.readParcelable(in, TrainParameters.class);
        this.places = in.createTypedArrayList(Place.CREATOR);
    }

    public static final Parcelable.Creator<TrainRoute> CREATOR = new Parcelable.Creator<TrainRoute>() {
        @Override
        public TrainRoute createFromParcel(Parcel source) {
            return new TrainRoute(source);
        }

        @Override
        public TrainRoute[] newArray(int size) {
            return new TrainRoute[size];
        }
    };
}
