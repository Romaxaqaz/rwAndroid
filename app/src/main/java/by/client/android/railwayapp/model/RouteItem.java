package by.client.android.railwayapp.model;

/**
 * Модель описания остановочного пункта по маршруту
 *
 * @author ROMAN PANTELEEV
 */
public class RouteItem {

    /**
     * Станция
     */
    private String stantion;

    /**
     * Время отправления
     */
    private String arrival;

    /**
     * Время прибытия
     */
    private String arrived;

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

    public RouteItem setStantion(String stantion) {
        this.stantion = stantion;
        return this;
    }

    public RouteItem setArrival(String arrival) {
        this.arrival = arrival;
        return this;
    }

    public RouteItem setArrived(String arrived) {
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

    public String getStantion() {
        return stantion;
    }

    public String getArrival() {
        return arrival;
    }

    public String getArrived() {
        return arrived;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public String getStay() {
        return stay;
    }
}
