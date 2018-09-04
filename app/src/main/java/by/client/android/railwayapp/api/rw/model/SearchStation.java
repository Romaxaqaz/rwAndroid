package by.client.android.railwayapp.api.rw.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import by.client.android.railwayapp.model.ParcelableUtils;

/**
 * Model to represent station of the train
 *
 * @author PRV
 */
@Entity(tableName = "stations")
public class SearchStation implements Parcelable {

    private String prefix;
    private String label;
    private String labelTail;
    private String value;
    private String gid;
    private Double lon;
    private Double lat;
    private String exp;
    private String ecp;
    private String otd;

    public SearchStation() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelTail() {
        return labelTail;
    }

    public void setLabelTail(String labelTail) {
        this.labelTail = labelTail;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getEcp() {
        return ecp;
    }

    public void setEcp(String ecp) {
        this.ecp = ecp;
    }

    public String getOtd() {
        return otd;
    }

    public void setOtd(String otd) {
        this.otd = otd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.prefix);
        dest.writeString(this.label);
        dest.writeString(this.labelTail);
        dest.writeString(this.value);
        dest.writeString(this.gid);
        dest.writeValue(this.lon);
        dest.writeValue(this.lat);
        dest.writeString(this.exp);
        dest.writeString(this.ecp);
        dest.writeValue(this.otd);
    }

    private SearchStation(Parcel in) {
        this.prefix = in.readString();
        this.label = in.readString();
        this.labelTail = in.readString();
        this.value = in.readString();
        this.gid = in.readString();
        this.lon = ParcelableUtils.readValue(in, Double.class);
        this.lat = ParcelableUtils.readValue(in, Double.class);
        this.exp = in.readString();
        this.ecp = in.readString();
        this.otd = ParcelableUtils.readValue(in, Object.class);
    }

    public static final Parcelable.Creator<SearchStation> CREATOR = new Parcelable.Creator<SearchStation>() {
        @Override
        public SearchStation createFromParcel(Parcel source) {
            return new SearchStation(source);
        }

        @Override
        public SearchStation[] newArray(int size) {
            return new SearchStation[size];
        }
    };
}