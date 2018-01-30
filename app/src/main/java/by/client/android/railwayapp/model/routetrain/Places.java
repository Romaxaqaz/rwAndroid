package by.client.android.railwayapp.model.routetrain;

/**
 * Created by PanteleevRV on 19.01.2018.
 */
public class Places {

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

    public Places(String reservedSeat, String compartment, String general) {
        this.reservedSeat = reservedSeat;
        this.compartment = compartment;
        this.general = general;

    }

    public String getReservedSeat() {
        return reservedSeat;
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

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }
}
