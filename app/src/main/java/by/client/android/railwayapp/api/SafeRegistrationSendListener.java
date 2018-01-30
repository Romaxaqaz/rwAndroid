package by.client.android.railwayapp.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import by.client.android.railwayapp.GlobalExceptionHandler;

/**
 * Реализация {@link RegisterLoader} - декоратор для других реализаций RegistrationSendListener.
 *
 * <p>Делегирует вызовы вложенной реализации и защищает сервис от исключений.</p>
 */
public class SafeRegistrationSendListener implements RegisterLoader {

    private static Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    private RegisterLoader delegate;

    public SafeRegistrationSendListener(RegisterLoader delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onStart() {
        try {
            delegate.onStart();
        }
        catch (Exception e) {
            logger.log(Level.ALL, "An exception in onStart() handler.", e);
        }
    }

    @Override
    public void onSuccess(Object response) {
        try {
            delegate.onSuccess(response);
        }
        catch (Exception e) {
            logger.log(Level.ALL, "An exception in onSuccess() handler.", e);
        }
    }

    @Override
    public void onFinish(boolean successful) {
        try {
            delegate.onFinish(successful);
        }
        catch (Exception e) {
            logger.log(Level.ALL, "An exception in onFinish() handler.", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        try {
            delegate.onError(throwable);
        }
        catch (Exception e) {
            logger.log(Level.ALL, "An exception in onError() handler.", e);
        }
    }
}
