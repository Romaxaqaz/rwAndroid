package by.client.android.railwayapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Описание сущности "Поезд"
 *
 * @author PRV
 */
public class Train implements Parcelable {

    /**
     * Номер поезда
     */
    private String id;

    /**
     * Маршрут
     */
    private String path;

    /**
     * Тип поезда
     */
    private String trainType;

    /**
     * Время прибытия
     */
    private String start;

    /**
     * Время отправления
     */
    private String end;

    /**
     * Нумерация вагонов
     */
    private String numbering;

    /**
     * Номер пути
     */
    private String way;

    /**
     * Номер платформы
     */
    private String platform;

    /**
     * Тип линии перевозок
     */
    private String pathType;

    private Train() {
    }

    public static Train createBuilder() {
        return new Train();
    }

    public Train setId(String id) {
        this.id = id;
        return this;
    }

    public Train setTrainType(String trainType) {
        this.trainType = trainType;
        return this;
    }

    public Train setStart(String start) {
        this.start = start;
        return this;
    }

    public Train setEnd(String end) {
        this.end = end;
        return this;
    }

    public Train setNumbering(String numbering) {
        this.numbering = numbering;
        return this;
    }

    public Train setPath(String path) {
        this.path = path;
        return this;
    }

    public Train setWay(String way) {
        this.way = way;
        return this;
    }

    public Train setPathType(String pathType) {
        this.pathType = pathType;
        return this;
    }

    public Train setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public String getTrainType() {
        return trainType;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getNumbering() {
        return numbering;
    }

    public String getWay() {
        return way;
    }

    public String getPathType() {
        return pathType;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.path);
        dest.writeString(this.trainType);
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeString(this.numbering);
        dest.writeString(this.way);
        dest.writeString(this.platform);
        dest.writeString(this.pathType);
    }

    private Train(Parcel in) {
        this.id = in.readString();
        this.path = in.readString();
        this.trainType = in.readString();
        this.start = in.readString();
        this.end = in.readString();
        this.numbering = in.readString();
        this.way = in.readString();
        this.platform = in.readString();
        this.pathType = in.readString();
    }

    public static final Parcelable.Creator<Train> CREATOR = new Parcelable.Creator<Train>() {
        @Override
        public Train createFromParcel(Parcel source) {
            return new Train(source);
        }

        @Override
        public Train[] newArray(int size) {
            return new Train[size];
        }
    };
}
