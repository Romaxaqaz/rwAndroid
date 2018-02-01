package by.client.android.railwayapp.api.rw.model;

/**
 * Модель для описания станции
 */
public class SearchStantion {

    private String prefix;
    private String label;
    private String labelTail;
    private String value;
    private String gid;
    private Double lon;
    private Double lat;
    private String exp;
    private String ecp;
    private Object otd;

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

    public Object getOtd() {
        return otd;
    }

    public void setOtd(Object otd) {
        this.otd = otd;
    }

}