package by.client.android.railwayapp.ui.page.traintimetable.history;

import android.provider.BaseColumns;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
public class TrainHistoryContract {

    public static class TrainEntry implements BaseColumns {

        public static final String TABLE_NAME = "trains";
        public static final String COLUMN_DEPARTURE = "departureStationName";
        public static final String COLUMN_DESTINATION = "destinationStationName";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_OBJECT = "object";
    }
}
