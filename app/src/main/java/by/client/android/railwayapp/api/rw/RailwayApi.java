package by.client.android.railwayapp.api.rw;

import java.util.List;

import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.api.rw.model.places.TrainPlaceInfo;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Api для взаимодействия с сервером БЖД
 *
 * <p>Основные запросы:</p>
 * <ul>
 *     <li>{@link RailwayApi#searchStantion(String, String, String)} - запрос станций удовлетрворяющих введенному названию</li>
 *     <li>{@link RailwayApi#getPlacesInfo(String)} - Запрос на получение полной информации о свободных местах поезда</li>
 *     <li>{@link RailwayApi#getScoreboardTable(String)} - Запрос на получения информации по прибытию и отправлению поездов заданной станции</li>
 *     <li>{@link RailwayApi#getTrainRoutePage(String, String)} - Запрос на получение маршрута поезда</li>
 *     <li>{@link RailwayApi#getTrainRoutes(String, String, String, String, String, String, String)} - Запрос на получение поездов по заданному маршруту</li>
 * </ul>
 *
 * @author PRV
 */
public interface RailwayApi {

    /**
     * Запрос на получение списка станций удовлетворяющих введенному названию
     *
     * @param text символы, входящие в название станции или полное наименование станции
     * @return список станций {@link SearchStantion}, удовлетворяющих введенному названию
     */
    @GET("/ru/ajax/autocomplete/search/")
    Call<List<SearchStantion>> searchStantion(@Query("term") String text, @Query("limit") String limit,
        @Query("timestamp") String timestamp);

    /**
     * Запрос на получение полной информации о свободных местах поезда
     *
     * @param value строка запроса: {@link Place#getLink()}
     * @return информация о свободных месстах {@link TrainPlaceInfo}
     */
    @GET("{value}")
    Call<TrainPlaceInfo> getPlacesInfo(@Path(value = "value", encoded = true) String value);

    /**
     * Запрос на получение информации о прибытии и отправлении поездов заданной станции
     *
     * @param stantionId идентификатор станции
     * @return возвращает {@link ResponseBody} для получения и парсинга html страницы
     */
    @GET("/ru/tablo")
    Call<ResponseBody> getScoreboardTable(@Query(value = "st_exp", encoded = true) String stantionId);

    /**
     * Запрос на получение маршрута поезда
     *
     * @param trainNumber номер поезда
     * @param date дата для поиска. По умолчанию everyday.
     * @return возвращает {@link ResponseBody} для получения и парсинга html страницы
     */
    @GET("/ru/train")
    Call<ResponseBody> getTrainRoutePage(@Query("train") String trainNumber, @Query("date") String date);

    /**
     * Запрос на получение поездов по заданному маршруту
     *
     * @param from станция отправления
     * @param to станция назначения
     * @param date дата поездки
     * @param from_exp идентификатор станции отправления
     * @param to_exp идентификатор станции назначения
     * @return возвращает {@link ResponseBody} для получения и парсинга html страницы
     */
    @GET("/ru/route/")
    Call<ResponseBody> getTrainRoutes(@Query("from") String from, @Query("to") String to, @Query("date") String date,
        @Query("from_exp") String from_exp, @Query("from_esr") String from_esr, @Query("to_exp") String to_exp,
        @Query("to_esr") String to_esr);
}
