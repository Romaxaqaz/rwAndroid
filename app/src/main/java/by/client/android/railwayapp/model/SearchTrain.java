package by.client.android.railwayapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PanteleevRV on 18.01.2018.
 *
 * @author Roman Panteleev
 */
public class SearchTrain implements Serializable {

    /**
     * Откуда
     */
    private String whence;

    /**
     * Куда
     */
    private String where;

    /**
     * Когда
     */
    private Date when;

    public SearchTrain(String whence, String where, Date when) {
        this.whence = whence;
        this.where = where;
        this.when = when;
    }

    public String getWhence() {
        return whence;
    }

    public void setWhence(String whence) {
        this.whence = whence;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }
}
