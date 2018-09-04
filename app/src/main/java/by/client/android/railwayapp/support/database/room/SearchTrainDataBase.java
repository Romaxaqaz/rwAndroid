package by.client.android.railwayapp.support.database.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import by.client.android.railwayapp.model.SearchTrain;

/**
 * Created by PanteleevRV on 04.09.2018.
 *
 * @author Q-RPA
 */
@Database(entities = {SearchTrain.class}, version = 1)
public abstract class SearchTrainDataBase extends RoomDatabase {

    public abstract SearchTrainDao searchTrainDao();
}
