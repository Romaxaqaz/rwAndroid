package by.client.android.railwayapp.ui.page.traintimetable.history;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.support.database.DataBase;
import by.client.android.railwayapp.support.database.SqlRequestBuilder;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;

import static by.client.android.railwayapp.ui.page.traintimetable.history.TrainHistoryContract.TrainEntry;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
public class TrainHistoryDataBase implements DataBase<SearchTrain> {

    private SQLiteOpenHelper sqLiteHelper;
    private Gson gson;

    public TrainHistoryDataBase(SQLiteOpenHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
        gson = new Gson();
    }

    @Override
    public void insert(SearchTrain searchTrain) {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        if (!isContain(searchTrain)) {
            ContentValues values = new ContentValues();
            values.put(TrainEntry.COLUMN_DEPARTURE, searchTrain.getDepartureStation().getValue());
            values.put(TrainEntry.COLUMN_DESTINATION, searchTrain.getDestinationStation().getValue());
            values.put(TrainEntry.COLUMN_DATE, new DateToStringConverter().convert(searchTrain.getDepartureDate()));
            values.put(TrainEntry.COLUMN_OBJECT, gson.toJson(searchTrain));

            db.insert(TrainEntry.TABLE_NAME, "", values);
        }
    }

    @Override
    public List<SearchTrain> getAll() {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

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

    @Override
    public void clear() {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        db.delete(TrainEntry.TABLE_NAME, null, null);
    }

    private boolean isContain(SearchTrain searchTrain) {
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

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
