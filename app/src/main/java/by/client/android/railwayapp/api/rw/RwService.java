package by.client.android.railwayapp.api.rw;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PanteleevRV on 31.01.2018.
 */

public class RwService {

    private static RwService instance = null;

    private static RwAPI rwAPI;
    private Retrofit retrofit;

    public static RwService getInstance() {
        if (instance == null) {
            instance = new RwService();
        }
        return instance;

    }

    public RwService() {
        retrofit = new Retrofit.Builder()
            .baseUrl("http://rasp.rw.by/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        rwAPI = retrofit.create(RwAPI.class);
    }

    public RwAPI getRwService() {
        return rwAPI;
    }

}
