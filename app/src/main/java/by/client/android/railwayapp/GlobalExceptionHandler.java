package by.client.android.railwayapp;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.os.Handler;
import by.client.android.railwayapp.ui.ParsingException;
import by.client.android.railwayapp.ui.utils.Dialogs;

/**
 * Класс для обработки глобальных ошибок
 *
 * @author ROMAN PANTELEEV
 */
public class GlobalExceptionHandler {

    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    private static final String DEFAULT_MESSAGE = "Произошла непредвиденная ошибка.";

    private Map<Class, Integer> exceptionMap = new HashMap<>();

    private Context context;

    public GlobalExceptionHandler(Context context) {
        this.context = context;

        exceptionMap.put(ParsingException.class, R.string.error_parsing_page);
        exceptionMap.put(UnknownHostException.class, R.string.error_connect);
    }

    public void handle(Exception exception) {
        logger.log(Level.ALL, exception.getMessage(), exception);

        Class exceptionClass = exception.getClass();
        if (exceptionMap.containsKey(exceptionClass)) {
            onDefaultThreadSafeHandler(context.getString(exceptionMap.get(exceptionClass)));
        }

        onDefaultThreadSafeHandler(DEFAULT_MESSAGE);
    }

    public void handle(Throwable throwable) {
        handle(new Exception(throwable));
    }

    private void onDefaultThreadSafeHandler(final Exception exception) {
        showError(exception.getMessage());
    }

    private void onDefaultThreadSafeHandler(final String message) {
        showError(message);
    }

    private void showError(final String message) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Dialogs.showToast(context, message);
            }
        }, 100);
    }
}
