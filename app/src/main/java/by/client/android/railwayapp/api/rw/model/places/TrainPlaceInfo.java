package by.client.android.railwayapp.api.rw.model.places;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PanteleevRV on 06.02.2018.
 *
 * @autor PRV
 */

public class TrainPlaceInfo {

    private Boolean isSimplePopup;
    private String carType;
    private String trainNumber;
    private String trainType;
    private Boolean isFastTrain;
    private String from;
    private String to;
    private String fromCode;
    private String toCode;
    private Route route;
    private Boolean hideCarImage;
    private Boolean allowOrder;
    private List<Tariff> tariffs = new ArrayList<>();

    public Boolean getIsSimplePopup() {
        return isSimplePopup;
    }

    public void setIsSimplePopup(Boolean isSimplePopup) {
        this.isSimplePopup = isSimplePopup;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public Boolean getIsFastTrain() {
        return isFastTrain;
    }

    public void setIsFastTrain(Boolean isFastTrain) {
        this.isFastTrain = isFastTrain;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Boolean getHideCarImage() {
        return hideCarImage;
    }

    public void setHideCarImage(Boolean hideCarImage) {
        this.hideCarImage = hideCarImage;
    }

    public Boolean getAllowOrder() {
        return allowOrder;
    }

    public void setAllowOrder(Boolean allowOrder) {
        this.allowOrder = allowOrder;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

}
