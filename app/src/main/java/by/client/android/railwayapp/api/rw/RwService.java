package by.client.android.railwayapp.api.rw;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Сервис для работы с запросами к серверу БЖД
 *
 * @author RPV
 */
public class RwService {

    private static final String BASE_URL = "http://rasp.rw.by/";
    private static RwService instance = null;

    private static RwAPI rwAPI;

    public static RwService getInstance() {
        if (instance == null) {
            instance = new RwService();
        }
        return instance;

    }

    private RwService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        rwAPI = retrofit.create(RwAPI.class);
    }

    public RwAPI getRwService() {
        return rwAPI;
    }
}
