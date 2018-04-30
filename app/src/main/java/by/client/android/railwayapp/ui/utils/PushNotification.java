package by.client.android.railwayapp.ui.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import by.client.android.railwayapp.R;

/**
 * Класс для отправки push нотификации, которая будет отображаться в строке состояния.
 *
 * @author Q-RPA
 */
public class PushNotification {

    private static final String DEFAULT_NOTIFICATION_CHANNEL = "default_notification_channel";

    private static int notificationId = 1;

    /**
     * Отправляет нотификацию для отображения в строке состояния
     *
     * @param context контекст приложения
     * @param message текст сообщения
     * @param pendingIntent страница для перехода по нажатию на сообщение
     */
    public static void send(Context context, String message, PendingIntent pendingIntent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = getNotification(context, message, pendingIntent);
        notificationManager.notify(getNotificationId(), notification);
    }

    private static Notification getNotification(Context context, String message, PendingIntent pendingintent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, DEFAULT_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(context.getString(R.string.app_name))
            .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
            .setAutoCancel(true)
            .setContentText(message);

        if (pendingintent != null) {
            builder.setContentIntent(pendingintent);
        }

        return builder.build();
    }

    private static synchronized int getNotificationId() {
        return notificationId++;
    }
}
