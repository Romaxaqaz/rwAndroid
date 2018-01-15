package by.client.android.railwayapp.api;

/**
 * Клиент для загрузки различных данных
 *
 * @author Roman Panteleev
 */
public class RailwayClient implements Client {

    @Override
    public void load(Loader loader) {
        loader.load();
    }
}
