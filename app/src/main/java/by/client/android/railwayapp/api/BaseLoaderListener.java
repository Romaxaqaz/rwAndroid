package by.client.android.railwayapp.api;

import java.lang.ref.WeakReference;

/**
 * Базовый класс слушателя загрузки данных
 *
 * @param <Context> ссылка на контекст данных
 * @param <Result> результат запроса
 *
 * @author ROMAN PANTELEEV
 */
public abstract class BaseLoaderListener<Context, Result> implements RegisterLoader {

    private final WeakReference<Context> contextReference;

    public BaseLoaderListener(Context context) {
        this.contextReference = new WeakReference(context);
    }

    @Override
    public void onStart() {
        this.onStart(this.contextReference.get());
    }

    @Override
    public void onSuccess(Object response) {
        this.onSuccess(this.contextReference.get(), (Result) response);
    }

    @Override
    public void onFinish(boolean successful) {
        this.onFinish(this.contextReference.get(), successful);
    }

    @Override
    public void onError(Throwable throwable) {
        this.onError(this.contextReference.get(), (Exception) throwable);
    }

    /**
     * Выполняется в самом начале загрузки
     *
     * @param context ссылка на контекст
     */
    protected void onStart(Context context) {

    }

    /**
     * Выполняется при успешной загрузки данных
     *
     * @param context ссылка на контекст
     * @param result результат загрузки
     */
    protected void onSuccess(Context context, Result result) {

    }

    /**
     * В случае ощибки выполнения запроса
     *
     * @param context ссылка на контекст
     * @param exception исключение
     */
    protected void onError(Context context, Exception exception) {

    }

    /**
     * Вызывается при завершении загрузки
     *
     * @param context ссылка на контекст
     * @param success успешное ли завершение
     */
    protected void onFinish(Context context, boolean success) {

    }
}
