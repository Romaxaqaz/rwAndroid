package by.client.android.railwayapp.support.database.room;

import java.util.Date;

import android.arch.persistence.room.TypeConverter;

/**
 * Created by PanteleevRV on 04.09.2018.
 *
 * @author Q-RPA
 */
public class DateConverters {

    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
