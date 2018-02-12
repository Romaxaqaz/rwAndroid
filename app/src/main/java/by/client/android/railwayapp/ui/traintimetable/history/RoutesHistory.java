package by.client.android.railwayapp.ui.traintimetable.history;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import by.client.android.railwayapp.model.SearchTrain;

/**
 * Класс для сохранения истории поиска поездоа
 *
 * @autor PRV
 */
public class RoutesHistory implements ObjectHistory<SearchTrain> {

    private static final String ROUTES_KEY = "ROUTES_KEY";

    private Context context;
    private List<SearchTrain> routeHistory = new ArrayList<>();

    public RoutesHistory(Context context) {
        this.context = context;
        routeHistory = readObject();
    }

    @Override
    public synchronized void add(SearchTrain searchTrain) {
        if (!isContain(searchTrain)) {
            routeHistory.add(searchTrain);
            writeObject(routeHistory);
        }
    }

    @Override
    public List<SearchTrain> getAll() {
        return routeHistory;
    }

    @Override
    public synchronized void clear() {
        routeHistory = new ArrayList<>();
        writeObject(routeHistory);
    }

    private Boolean isContain(SearchTrain searchTrain) {
        for (SearchTrain train : routeHistory) {
            if (train.equals(searchTrain)) {
                return true;
            }
        }
        return false;
    }

    private void writeObject(List<SearchTrain> searchTrains) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(ROUTES_KEY, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(searchTrains);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<SearchTrain> readObject() {
        try {
            FileInputStream fileInputStream = context.openFileInput(ROUTES_KEY);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<SearchTrain>) objectInputStream.readObject();
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
