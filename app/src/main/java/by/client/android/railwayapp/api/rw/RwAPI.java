package by.client.android.railwayapp.api.rw;

import java.util.List;

import by.client.android.railwayapp.api.rw.model.SearchStantion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api для взаимодействия с серверо БЖД
 *
 * Основные запросы:
 * <ul>
 *     <li>{@link RwAPI#searchStantion(String, String, String)} - запрос станций удовлетрворяющих введенному названию</li>
 * </ul>
 *
 * @author PRV
 */
public interface RwAPI {

    /**
     * Запрос на получение списка станций удовлетворяющим введенному названию
     * @param text символы, входящие в название станции или полное наименование станции
     * @return список станций удовлетворяющих введенному названию
     */
    @GET("/ru/ajax/autocomplete/search/")
    Call<List<SearchStantion>> searchStantion(@Query("term") String text, @Query("limit") String limit,
        @Query("timestamp") String timestamp);
}
