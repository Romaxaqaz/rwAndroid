package by.client.android.railwayapp.support.database;

import java.util.List;

/**
 * Created by PanteleevRV on 13.03.2018.
 *
 * @author PRV
 */
public interface DataBase<T> {

    void insert(T item);

    List<T> getAll();

    void clear();
}
