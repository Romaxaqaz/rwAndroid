package by.client.android.railwayapp.support;

/**
 * Клиент для загрузки различных данных
 *
 * @author ROMAN PANTELEEV
 */
public class RailwayClient implements Client {

    @Override
    public void load(Loader loader) {
        loader.load();
    }
}
