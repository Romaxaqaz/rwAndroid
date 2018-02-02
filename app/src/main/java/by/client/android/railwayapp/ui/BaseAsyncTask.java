package by.client.android.railwayapp.ui;

import android.os.AsyncTask;

/**
 * Базовый класс для реализации асинхронных запросов с возможностью отображения индикатора выполнения
 * <p>Основные методы класса.</p>
 * <ul>
 *     <li>{@link BaseAsyncTask#runTask(Object[])} выполняет задачу в фоне</li>
 *     <li>{@link BaseAsyncTask#onCompleted(Object)} выполняется после успешного выполнения задачи</li>
 *     <li>{@link BaseAsyncTask#onFinish(boolean)} срабатывает после выполнения задачи</li>
 *     <li>{@link BaseAsyncTask#onError(Exception)} выполняется при возникновении ошибки при выполнении задачи</li>
 * </ul>
 *
 * @param <Params> тип входного параметра
 * @param <Result> тип возвращаемого результата при успешном выполнении задачи
 *
 * @author ROMAN PANTELEEV
 */
public abstract class BaseAsyncTask<Params, Result> extends AsyncTask<Params, Void, Result> {

    private Exception exception = null;

    /**
     * Метод для обработки завершения операции
     *
     * @param param результат выполения
     */
    protected abstract void onCompleted(Result param);

    /**
     * Метод срабатывающий перез выполнением асинхронной задачи
     */
    protected abstract void onStart();

    /**
     * Метод срабатывает после выполнения асинхронной задачи
     *
     * @param successful индикатор успешного выполнения задачи
     */
    protected abstract void onFinish(boolean successful);

    /**
     * Метод выполнения операции
     *
     * @param param параметры для выполнения операции
     */
    protected abstract Result runTask(Params... param) throws Exception;

    /**
     * Метод для обработки ошибки выполнения
     *
     * @param exception ошибка при выполнении операции
     */
    protected abstract void onError(Exception exception);

    @Override
    protected Result doInBackground(Params... params) {

        try {
            return runTask(params);
        }
        catch (Exception ex) {
            exception = ex;
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onStart();
    }

    @Override
    protected void onPostExecute(Result response) {
        super.onPostExecute(response);

        if (exception == null) {
            onCompleted(response);
        } else {
            onError(exception);
        }

        onFinish(exception == null);
    }
}
