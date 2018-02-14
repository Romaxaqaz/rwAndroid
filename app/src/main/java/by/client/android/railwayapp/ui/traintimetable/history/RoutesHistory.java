package by.client.android.railwayapp.ui.traintimetable.history;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import by.client.android.railwayapp.model.SearchTrain;

/**
 * Класс для сохранения истории поиска поездоа
 *
 * <p>Сохраняет уникальный запрос поезда {@link SearchTrain}</p>
 *
 * @author PRV
 */
public class RoutesHistory implements ObjectHistory<SearchTrain> {

    private static Logger logger = Logger.getLogger(RoutesHistory.class.getName());

    private static final String ROUTES_KEY = "ROUTES_KEY";

    private Context context;
    private List<SearchTrain> routeHistory = new ArrayList<>();

    public RoutesHistory(Context context) {
        this.context = context;
        routeHistory = readCache();
    }

    @Override
    public synchronized void add(SearchTrain searchTrain) {
        if (!isContain(searchTrain)) {
            routeHistory.add(searchTrain);
            saveState();
        }
    }

    @Override
    public List<SearchTrain> getAll() {
        return routeHistory;
    }

    @Override
    public synchronized void clear() {
        routeHistory = new ArrayList<>();
        saveState();
    }

    private Boolean isContain(SearchTrain searchTrain) {
        for (SearchTrain train : routeHistory) {
            if (train.equals(searchTrain)) {
                return true;
            }
        }
        return false;
    }

    private void saveState() {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(ROUTES_KEY, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(routeHistory);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException exception) {
            logger.log(Level.WARNING, "Error while saving query history cache", exception);
        }
    }

    private List<SearchTrain> readCache() {
        try {
            FileInputStream fileInputStream = context.openFileInput(ROUTES_KEY);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<SearchTrain>) objectInputStream.readObject();
        }
        catch (ClassNotFoundException | IOException exception) {
            logger.log(Level.WARNING, "Error while reading query history cache", exception);
        }
        return new ArrayList<>();
    }
}
