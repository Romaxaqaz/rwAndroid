package by.client.android.railwayapp.model;

import java.util.Date;

/**
 * Модель описания остановочного пункта по маршруту
 *
 * @author ROMAN PANTELEEV
 */
public class RouteItem {

    /**
     * Станция
     */
    private String station;

    /**
     * Время отправления
     *
     * <p>Корректный формат отображения <code>HH:mm</code></p>
     */
    private Date arrival;

    /**
     * Время прибытия
     *
     * <p>Корректный формат отображения <code>HH:mm</code></p>
     */
    private Date arrived;

    /**
     * Время в пути
     */
    private String travelTime;

    /**
     * Время стоянки
     */
    private String stay;

    private RouteItem() {
    }

    public static RouteItem createBuilder() {
        return new RouteItem();
    }

    public RouteItem setStation(String station) {
        this.station = station;
        return this;
    }

    public RouteItem setArrival(Date arrival) {
        this.arrival = arrival;
        return this;
    }

    public RouteItem setArrived(Date arrived) {
        this.arrived = arrived;
        return this;
    }

    public RouteItem setTravelTime(String travelTime) {
        this.travelTime = travelTime;
        return this;
    }

    public RouteItem setStay(String stay) {
        this.stay = stay;
        return this;
    }

    public String getStation() {
        return station;
    }

    public Date getArrival() {
        return arrival;
    }

    public Date getArrived() {
        return arrived;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public String getStay() {
        return stay;
    }
}
