package by.client.android.railwayapp.ui.traintimetable.history;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.support.database.DataBase;
import by.client.android.railwayapp.support.database.SqlRequestBuilder;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import static by.client.android.railwayapp.ui.traintimetable.history.TrainHistoryContract.TrainEntry;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
public class TrainHistoryDataBase implements DataBase<SearchTrain> {

    private TrainHistoryDbHelper trainHistoryDbHelper;
    private Gson gson;

    public TrainHistoryDataBase(Context context) {
        trainHistoryDbHelper = new TrainHistoryDbHelper(context);
        gson = new Gson();
    }

    public void insert(SearchTrain searchTrain) {
        SQLiteDatabase db = trainHistoryDbHelper.getWritableDatabase();

        if (!isContain(searchTrain)) {
            ContentValues values = new ContentValues();
            values.put(TrainEntry.COLUMN_DEPARTURE, searchTrain.getDepartureStation().getValue());
            values.put(TrainEntry.COLUMN_DESTINATION, searchTrain.getDestinationStation().getValue());
            values.put(TrainEntry.COLUMN_DATE, new DateToStringConverter().convert(searchTrain.getDepartureDate()));
            values.put(TrainEntry.COLUMN_OBJECT, gson.toJson(searchTrain));

            db.insert(TrainEntry.TABLE_NAME, "", values);
        }
    }

    public List<SearchTrain> getAll() {
        SQLiteDatabase db = trainHistoryDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(new SqlRequestBuilder()
            .select()
            .from(TrainEntry.TABLE_NAME)
            .orderBy("date(" + TrainEntry.COLUMN_DATE + ") DESC").build(), null);

        List<SearchTrain> searchTrains = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndex(TrainEntry.COLUMN_OBJECT));
                searchTrains.add(gson.fromJson(data, SearchTrain.class));
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        return searchTrains;
    }

    public void clear() {
        SQLiteDatabase db = trainHistoryDbHelper.getWritableDatabase();
        db.delete(TrainEntry.TABLE_NAME, null, null);
    }

    private boolean isContain(SearchTrain searchTrain) {
        SQLiteDatabase db = trainHistoryDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(new SqlRequestBuilder()
            .select()
            .from(TrainEntry.TABLE_NAME)
            .where(TrainEntry.COLUMN_DEPARTURE)
            .like(searchTrain.getDepartureStation().getValue())
            .and(TrainEntry.COLUMN_DESTINATION)
            .like(searchTrain.getDestinationStation().getValue())
            .build(), null);

        boolean result = cursor.getCount() > 0;
        cursor.close();

        return result;
    }
}
