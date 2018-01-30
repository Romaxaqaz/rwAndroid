package by.client.android.railwayapp.api.biletix;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PanteleevRV on 30.01.2018.
 */
public class BiletixService {

    private static final String BASE_URL = "https://ibe.biletix.ru/";

    private static BiletixAPI biletixAPI;
    private Retrofit retrofit;

    public BiletixService() {
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        biletixAPI = retrofit.create(BiletixAPI.class);
    }

    public BiletixAPI getBiletixAPI() {
        return biletixAPI;
    }
}
