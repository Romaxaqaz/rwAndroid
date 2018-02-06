package by.client.android.railwayapp.ui;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback обработки загрузки Retrofit2
 *
 * <p>Основные методы</p>
 * <ul>
 *     <li>{@link RetrofitCallback#onStart()} - вызывается при старте процесса загрузки. Необязателен для реализации</li>
 *     <li>{@link RetrofitCallback#onComplete(Call, Response)} - вызывается при успешной загрузки данных</li>
 *     <li>{@link RetrofitCallback#onError(Call, Throwable)} - вызывается в случае ошибки загрузки данных</li>
 *     <li>{@link RetrofitCallback#onFinish()} - вызывается при завершении загрузки</li>
 * </ul>
 *
 * @param T дип возвращаемого значения
 * @autor PRV
 */
public abstract class RetrofitCallback<T> implements Callback<T> {

    protected RetrofitCallback() {
        onStart();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onComplete(call, response);
        onFinish();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(call, t);
        onFinish();
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public abstract void onComplete(Call<T> call, Response<T> response);

    public abstract void onError(Call<T> call, Throwable t);
}
