package by.client.android.railwayapp.ui;

/**
 *  Интерфейс для реализации индикатора загрузки данных в {@link BaseAsyncTask}
 *
 *  @author Q-RPA
 */
public interface ProgressIndicator {

    /**
     * Отображает индикатор
     */
    void show();

    /**
     * Скрывает индикатор
     */
    void hide();
}
