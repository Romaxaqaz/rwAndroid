package by.client.android.railwayapp.model.routetrain;

/**
 * Created by PanteleevRV on 19.01.2018.
 */
public class TrainTime {

    /**
     * Время прибытия
     */
    private String arrival;

    /**
     * Время отправления
     */
    private String arrived;

    public TrainTime(String arrival, String arrived) {
        this.arrival = arrival;
        this.arrived = arrived;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrived() {
        return arrived;
    }

    public void setArrived(String arrived) {
        this.arrived = arrived;
    }
}
