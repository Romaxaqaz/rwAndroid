package by.client.android.railwayapp.api.rw;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Сервис для получения API для работы с сервером БЖД
 *
 * @author RPV
 */
public class RailwayService {

    private static final String BASE_URL = "http://rasp.rw.by/";

    public RailwayApi getRailwayApi() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit.create(RailwayApi.class);
    }
}
