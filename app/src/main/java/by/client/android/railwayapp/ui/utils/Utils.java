package by.client.android.railwayapp.ui.utils;

/**
 * Created by PanteleevRV on 13.02.2018.
 *
 * @author  PRV
 */
public class Utils {

    public static boolean isBlank(String string) {
        return string == null || "".equals(string.trim());
    }

    public static void delay(long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
    }

    public static void handleThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        } else if (throwable instanceof Error) {
            throw (Error) throwable;
        } else {
            throw new IllegalStateException("Checked exception of type '" + throwable.getClass() +
                "' passed to handleThrowable()", throwable);
        }
    }
}
