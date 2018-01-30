package by.client.android.railwayapp.model.routetrain;

import java.util.List;

/**
 * Created by PanteleevRV on 19.01.2018.
 *
 * @author Roman Panteleev
 */
public class TrainRoute {

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
}
