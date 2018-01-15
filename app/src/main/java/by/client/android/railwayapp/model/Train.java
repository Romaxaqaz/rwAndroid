package by.client.android.railwayapp.model;

import java.io.Serializable;

public class Train implements Serializable {

    private String name;
    private String type;
    private String time;
    private String numbering;
    private String path;
    private String platform;

    public Train(String name, String type, String time, String numbering, String path, String platform) {
        this.name = name;
        this.type = type;
        this.time = time;
        this.numbering = numbering;
        this.path = path;
        this.platform = platform;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNumbering(String numbering) {
        this.numbering = numbering;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getNumbering() {
        return numbering;
    }

    public String getPath() {
        return path;
    }

    public String getPlatform() {
        return platform;
    }

    @Override
    public String toString() {
        return "Train{" +
            "name='" + name + '\'' +
            ", type='" + type + '\'' +
            '}';
    }
}
