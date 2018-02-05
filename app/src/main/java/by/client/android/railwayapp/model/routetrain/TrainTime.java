package by.client.android.railwayapp.model.routetrain;

import java.io.Serializable;

/**
 * Модель описания времени прибытия и отправления
 *
 * @author PRV
 */
public class TrainTime implements Serializable {

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
