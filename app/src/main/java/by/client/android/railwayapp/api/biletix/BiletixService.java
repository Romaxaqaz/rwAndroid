package by.client.android.railwayapp.api.biletix;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Сервис для работы с запросами к серверу biletix.by
 *
 * @author RPV
 */
public class BiletixService {

    private static final String BASE_URL = "https://ibe.biletix.ru/";

    private static BiletixAPI biletixAPI;

    public BiletixService() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        biletixAPI = retrofit.create(BiletixAPI.class);
    }

    public BiletixAPI getBiletixAPI() {
        return biletixAPI;
    }
}
