package by.client.android.railwayapp.api.rw;

import java.util.List;

import by.client.android.railwayapp.api.rw.model.SearchStantion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by PanteleevRV on 31.01.2018.
 */

public interface RwAPI {
    @GET("/ru/ajax/autocomplete/search/")
    Call<List<SearchStantion>> searchStantion(@Query("term") String text, @Query("limit") String limit, @Query("timestamp") String timestamp);
}
