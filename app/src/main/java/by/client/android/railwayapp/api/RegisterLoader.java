package by.client.android.railwayapp.api;

/**
 * Callback обработчика загрузки данных
 *
 * @author Roman Panteleev
 */
public interface RegisterLoader {

    /**
     * Вызывается при старте процесса загрузки
     */
    void onStart();

    /**
     * Вызывается при успешной загрузки данных
     *
     * @param response результат загрузки
     */
    void onSuccess(Object response);

    /**
     * Вызывается при завершении загрузки
     *
     * @param successful
     */
    void onFinish(boolean successful);

    /**
     * Вызывается при возникновении любой ошибки в процессе загрузки
     *
     * @param throwable
     */
    void onError(Throwable throwable);
}
