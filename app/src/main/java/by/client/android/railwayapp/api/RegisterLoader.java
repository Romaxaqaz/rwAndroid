package by.client.android.railwayapp.api;

/**
 * Callback обработчика загрузки данных
 *
 * @author ROMAN PANTELEEV
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
     */
    void onFinish(boolean successful);

    /**
     * Вызывается при возникновении любой ошибки в процессе загрузки
     */
    void onError(Throwable throwable);
}
