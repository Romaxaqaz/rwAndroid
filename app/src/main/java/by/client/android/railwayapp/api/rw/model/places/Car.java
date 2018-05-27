package by.client.android.railwayapp.api.rw.model.places;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of representation of seats in the car
 *
 * @author PRV
 */
public class Car {

    private String number;
    private String carrier;
    private String owner;
    private String upperPlaces;
    private String upperSidePlaces;
    private String lowerPlaces;
    private String lowerSidePlaces;
    private Integer totalPlaces;
    private Boolean totalPlacesHide;
    private List<String> emptyPlaces = new ArrayList<>();
    private Boolean hideLegend;
    private String imgSrc;
    private String hash;
    private Boolean noSmoking;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUpperPlaces() {
        return upperPlaces;
    }

    public String getUpperSidePlaces() {
        return upperSidePlaces;
    }

    public String getLowerPlaces() {
        return lowerPlaces;
    }

    public String getLowerSidePlaces() {
        return lowerSidePlaces;
    }

    public List<String> getEmptyPlaces() {
        return emptyPlaces;
    }

    public Boolean getHideLegend() {
        return hideLegend;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getHash() {
        return hash;
    }

    public Boolean getNoSmoking() {
        return noSmoking;
    }

}
