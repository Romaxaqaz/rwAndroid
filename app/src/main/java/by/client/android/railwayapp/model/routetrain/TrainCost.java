package by.client.android.railwayapp.model.routetrain;

/**
 * Класс для представления стоимости проезда
 *
 * @author Roman Panteleev
 */
public class TrainCost {

    /**
     * Плацкарт
     */
    private String reservedSeat;

    /**
     * Купе
     */
    private String compartment;

    /**
     * Общее
     */
    private String general;

    public TrainCost(String reservedSeat, String compartment, String general) {
        this.reservedSeat = reservedSeat;
        this.compartment = compartment;
        this.general = general;
    }

    public String getReservedSeat() {
        return reservedSeat;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public void setReservedSeat(String reservedSeat) {
        this.reservedSeat = reservedSeat;
    }

    public String getCompartment() {
        return compartment;
    }

    public void setCompartment(String compartment) {
        this.compartment = compartment;
    }
}
