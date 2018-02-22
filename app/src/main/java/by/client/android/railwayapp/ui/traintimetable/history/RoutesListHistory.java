package by.client.android.railwayapp.ui.traintimetable.history;

import java.util.ArrayList;
import java.util.List;

import by.client.android.railwayapp.model.SearchTrain;

/**
 * Класс для сохранения истории поиска поездоа
 *
 * <p>Сохраняет уникальный запрос поезда {@link SearchTrain}</p>
 *
 * @author PRV
 */
public class RoutesListHistory implements ObjectListHistory<SearchTrain> {

    private ObjectCache<List<SearchTrain>> objectCache;
    private List<SearchTrain> routeHistory = new ArrayList<>();

    public RoutesListHistory(ObjectCache<List<SearchTrain>> objectCache) {
        routeHistory = new ArrayList<>();
        this.objectCache = objectCache;
        updateRoutes(objectCache);
    }

    private void updateRoutes(ObjectCache<List<SearchTrain>> objectCache) {
        if (objectCache.read() != null) {
            routeHistory = objectCache.read();
        }
    }

    @Override
    public synchronized void add(SearchTrain searchTrain) {
        if (!isContain(searchTrain)) {
            routeHistory.add(searchTrain);
            objectCache.save(routeHistory);
        }
    }

    @Override
    public List<SearchTrain> getAll() {
        return routeHistory;
    }

    @Override
    public synchronized void clear() {
        routeHistory = new ArrayList<>();
        objectCache.save(routeHistory);
    }

    private Boolean isContain(SearchTrain searchTrain) {
        for (SearchTrain train : routeHistory) {
            if (train.equals(searchTrain)) {
                return true;
            }
        }
        return false;
    }
}
