package by.client.android.railwayapp.support;

import java.lang.ref.WeakReference;

import io.reactivex.observers.DisposableObserver;

public class BaseRxObserverListener<Context, Result> extends DisposableObserver<Result> {

    private final WeakReference<Context> contextReference;

    public BaseRxObserverListener(Context context) {
        this.contextReference = new WeakReference(context);
    }


    @Override
    protected void onStart() {
        this.onStart(this.contextReference.get());
    }

    @Override
    public void onNext(Result value) {
        this.onSuccess(this.contextReference.get(), value);
    }

    @Override
    public void onError(Throwable e) {
        this.onError(this.contextReference.get(), (Exception) e);
    }

    @Override
    public void onComplete() {
        this.onFinish(this.contextReference.get());
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
     * @param result  результат загрузки
     */
    protected void onSuccess(Context context, Result result) {

    }

    /**
     * В случае ощибки выполнения запроса
     *
     * @param context   ссылка на контекст
     * @param exception исключение
     */
    protected void onError(Context context, Exception exception) {

    }

    /**
     * Вызывается при завершении загрузки
     *
     * @param context ссылка на контекст
     */
    protected void onFinish(Context context) {

    }
}
