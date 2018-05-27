package by.client.android.railwayapp.api.rw.model.places;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to represent the cost of travel
 *
 * @author PRV
 */
public class Tariff {

    private String price;
    private String typeAbbr;
    private String typeAbbrPostfix;
    private Boolean isBicycle;
    private String type;
    private String typeAbbrInt;
    private String description;
    private String sign;

    @JsonProperty("is_car_for_disabled")
    private Boolean isCarForDisabled;

    @JsonProperty("price_byn")
    private String priceByn;

    private List<Car> cars = new ArrayList<>();

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeAbbr() {
        return typeAbbr;
    }

    public void setTypeAbbr(String typeAbbr) {
        this.typeAbbr = typeAbbr;
    }

    public String getTypeAbbrPostfix() {
        return typeAbbrPostfix;
    }

    public void setTypeAbbrPostfix(String typeAbbrPostfix) {
        this.typeAbbrPostfix = typeAbbrPostfix;
    }

    public Boolean getIsBicycle() {
        return isBicycle;
    }

    public void setIsBicycle(Boolean isBicycle) {
        this.isBicycle = isBicycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeAbbrInt() {
        return typeAbbrInt;
    }

    public void setTypeAbbrInt(String typeAbbrInt) {
        this.typeAbbrInt = typeAbbrInt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Boolean getIsCarForDisabled() {
        return isCarForDisabled;
    }

    public void setIsCarForDisabled(Boolean isCarForDisabled) {
        this.isCarForDisabled = isCarForDisabled;
    }

    @JsonProperty("price_byn")
    public String getPriceByn() {
        return priceByn;
    }

    @JsonProperty("price_byn")
    public void setPriceByn(String priceByn) {
        this.priceByn = priceByn;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
