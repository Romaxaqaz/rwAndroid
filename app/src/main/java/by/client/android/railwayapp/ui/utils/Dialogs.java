package by.client.android.railwayapp.ui.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Утилитный класс для работы с диалогами
 *
 * @author  PRV
 */
public class Dialogs {

    /**
     * Отображает Toast сообщение
     *
     * <p>Время отображения {@link Toast#LENGTH_SHORT}</p>
     *
     * @param context контекст данных
     * @param messageId идентификатор сообщения
     */
    public static void showToast(Context context, int messageId) {
        Toast.makeText(context.getApplicationContext(), messageId, Toast.LENGTH_SHORT).show();
    }

    /**
     * Отображает Toast сообщение
     *
     * <p>Время отображения {@link Toast#LENGTH_SHORT}</p>
     *
     * @param context контекст данных
     * @param CharSequence текст сообщения
     */
    public static Toast showToast(Context context, CharSequence message) {
        Toast toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    /**
     * Отображает диалоговое окно с действием "ok"
     *
     * @param context контекст данных
     * @param title заголовок диалога
     * @param text текст диалога
     */
    public static void showOkDialog(Context context, CharSequence title, CharSequence text) {
        new AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(text)
            .setPositiveButton("Ok", null)
            .show();
    }
}
