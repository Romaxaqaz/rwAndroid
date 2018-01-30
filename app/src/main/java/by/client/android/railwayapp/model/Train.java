package by.client.android.railwayapp.model;

import java.io.Serializable;

/**
 * Описание сущности "Поезд"
 */
public class Train implements Serializable {

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
}
