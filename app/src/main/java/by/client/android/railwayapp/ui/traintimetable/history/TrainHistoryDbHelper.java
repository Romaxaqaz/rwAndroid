package by.client.android.railwayapp.ui.traintimetable.history;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static by.client.android.railwayapp.ui.traintimetable.history.TrainHistoryContract.TrainEntry;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
public class TrainHistoryDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TrainHistory.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TrainEntry.TABLE_NAME + " (" +
        TrainEntry._ID + " INTEGER PRIMARY KEY," +
        TrainEntry.COLUMN_DEPARTURE + TEXT_TYPE + COMMA_SEP +
        TrainEntry.COLUMN_DESTINATION + TEXT_TYPE + COMMA_SEP +
        TrainEntry.COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
        TrainEntry.COLUMN_OBJECT + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + TrainEntry.TABLE_NAME;

    public TrainHistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
