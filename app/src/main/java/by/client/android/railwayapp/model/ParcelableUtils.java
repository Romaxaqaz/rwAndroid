package by.client.android.railwayapp.model;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Утилитный класс для работы с {@link Parcelable}
 *
 * @author PRV
 */
public class ParcelableUtils {

    public static <T> T readValue(Parcel parcelable, Class clas) {
        return (T) parcelable.readValue(clas.getClassLoader());
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcelable, Class clas) {
        return parcelable.readParcelable(clas.getClassLoader());
    }

    public static Date readDate(Parcel parcelable) {
        long tmpDepartureDate = parcelable.readLong();
        return tmpDepartureDate == -1 ? null : new Date(tmpDepartureDate);
    }

    public static long getDateTime(Date date) {
        return date != null ? date.getTime() : -1;
    }
}
