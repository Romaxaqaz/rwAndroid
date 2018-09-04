package by.client.android.railwayapp.support.database.room;

import java.util.List;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import by.client.android.railwayapp.model.SearchTrain;
import io.reactivex.Maybe;


/**
 * Created by PanteleevRV on 04.09.2018.
 *
 * @author Q-RPA
 */
@Dao
public interface SearchTrainDao {

    @Query("SELECT * FROM searchtrain")
    Maybe<List<SearchTrain>> getAll();

    @Insert
    void insert(SearchTrain... employee);

    @Query("DELETE FROM searchtrain")
    void nukeTable();
}
