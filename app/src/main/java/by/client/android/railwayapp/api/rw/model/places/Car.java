package by.client.android.railwayapp.api.rw.model.places;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PanteleevRV on 06.02.2018.
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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUpperPlaces() {
        return upperPlaces;
    }

    public void setUpperPlaces(String upperPlaces) {
        this.upperPlaces = upperPlaces;
    }

    public String getUpperSidePlaces() {
        return upperSidePlaces;
    }

    public void setUpperSidePlaces(String upperSidePlaces) {
        this.upperSidePlaces = upperSidePlaces;
    }

    public String getLowerPlaces() {
        return lowerPlaces;
    }

    public void setLowerPlaces(String lowerPlaces) {
        this.lowerPlaces = lowerPlaces;
    }

    public String getLowerSidePlaces() {
        return lowerSidePlaces;
    }

    public void setLowerSidePlaces(String lowerSidePlaces) {
        this.lowerSidePlaces = lowerSidePlaces;
    }

    public Integer getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(Integer totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public Boolean getTotalPlacesHide() {
        return totalPlacesHide;
    }

    public void setTotalPlacesHide(Boolean totalPlacesHide) {
        this.totalPlacesHide = totalPlacesHide;
    }

    public List<String> getEmptyPlaces() {
        return emptyPlaces;
    }

    public void setEmptyPlaces(List<String> emptyPlaces) {
        this.emptyPlaces = emptyPlaces;
    }

    public Boolean getHideLegend() {
        return hideLegend;
    }

    public void setHideLegend(Boolean hideLegend) {
        this.hideLegend = hideLegend;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Boolean getNoSmoking() {
        return noSmoking;
    }

    public void setNoSmoking(Boolean noSmoking) {
        this.noSmoking = noSmoking;
    }
}
