package by.client.android.railwayapp.ui.scoreboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import by.client.android.railwayapp.ui.utils.PushNotification;

public class TimeService extends Service {

    public static final long NOTIFY_INTERVAL = 10000;

    private Handler mHandler = new Handler();
    private Timer mTimer = null;

    private Date dateArrive;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String userID = intent.getStringExtra("TIME_IN_MINUTES");
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try {
            dateArrive = dateFormat.parse(userID);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            mTimer = new Timer();
        }
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);

    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    Date local = Calendar.getInstance().getTime();
                    long diff = dateArrive.getTime() - local.getTime();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(diff);
                    int min = calendar.get(Calendar.MINUTE);
                    int sec = calendar.get(Calendar.SECOND);

                    PushNotification.send(getApplicationContext(), getDateTime(min, sec), null, 998);
                }
            });
        }

        private String getDateTime(int min, int second) {
            return String.format("До отправления: %s м, %s с", min, second);
        }

    }
}
