package by.client.android.railwayapp.ui.traintimetable.history;

import android.provider.BaseColumns;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
final class TrainHistoryContract {

    static class TrainEntry implements BaseColumns {

        static final String TABLE_NAME = "trains";
        static final String COLUMN_DEPARTURE = "departureStationName";
        static final String COLUMN_DESTINATION = "destinationStationName";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_OBJECT = "object";
    }
}
