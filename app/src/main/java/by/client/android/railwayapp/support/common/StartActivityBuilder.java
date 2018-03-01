package by.client.android.railwayapp.support.common;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Билдер для построения и запуска Activity
 *
 * @author PRV
 */
public class StartActivityBuilder {

    private final Activity currentActivity;
    private final Class<? extends Activity> needActivity;
    private final Bundle rawIntentParams;

    private StartActivityBuilder(Activity currentActivity, Class<? extends Activity> needActivity) {
        this.currentActivity = currentActivity;
        this.needActivity = needActivity;
        rawIntentParams = new Bundle();
    }

    /**
     * Создает экземпляр билдера
     *
     * @param currentActivity текущая страница
     * @param needActivity класс страницы на которую необходимо перейти
     */
    public static StartActivityBuilder create(Activity currentActivity, Class<? extends Activity> needActivity) {
        return new StartActivityBuilder(currentActivity, needActivity);
    }

    /**
     * Добавляет параметр в Intent
     *
     * @param key ключ параметра
     * @param param значение параметра
     * @param <T> тип параметра
     */
    public <T extends Serializable> StartActivityBuilder param(String key, T param) {
        rawIntentParams.putSerializable(key, param);
        return this;
    }

    /**
     * Формирует Intent и выполняет навигацию
     */
    public void start() {
        currentActivity.startActivity(createIntent());
    }

    /**
     * Формирует Intent и выполняет навигацию с установкой кода запроса
     */
    public void startForResult(int requestCode) {
        currentActivity.startActivityForResult(createIntent(), requestCode);
    }

    private Intent createIntent() {
        Intent intent = new Intent(currentActivity, needActivity);
        intent.putExtras(rawIntentParams);
        return intent;
    }
}
