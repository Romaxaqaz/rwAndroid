package by.client.android.railwayapp.ui.scoreboard;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.utils.PushNotification;

/**
 * Страница для отображения детальной информации поезда со страницы "Виртуально табло"
 *
 * @author ROMAN PANTELEEV
 */
@EActivity(R.layout.activity_scroreboard_detail)
public class ScroreboardDetailActivity extends AppCompatActivity {

    private static final String TRAIN_KEY = "TRAIN_KEY";

    @ViewById(R.id.icon)
    ImageView ico;

    @ViewById(R.id.id)
    TextView id;

    @ViewById(R.id.way)
    TextView way;

    @ViewById(R.id.path)
    TextView path;

    @ViewById(R.id.type)
    TextView type;

    @ViewById(R.id.trainPlatform)
    TextView trainPlatform;

    @ViewById(R.id.startTime)
    TextView startTime;

    @ViewById(R.id.endTime)
    TextView endTime;

    @Extra(TRAIN_KEY)
    Train train;

    public static void start(Activity activity, Train train, int requestCode) {
        Intent intent = new Intent(activity, ScroreboardDetailActivity_.class);
        intent.putExtra(ScroreboardDetailActivity.TRAIN_KEY, train);
        activity.startActivityForResult(intent, requestCode);
    }

    @AfterViews
    void initActivity() {
        getSupportActionBar().setHomeButtonEnabled(true);

        id.setText(train.getId());
        ico.setBackgroundResource(new TrainTypeToImage().convert(train.getPathType()));
        type.setText(train.getTrainType());
        path.setText(train.getPath());
        way.setText(train.getWay());
        trainPlatform.setText(train.getPlatform());
        startTime.setText(train.getStart());
        endTime.setText(train.getEnd());
    }

    @Click(R.id.sendPush)
    void submitButton(View view) {
        if (view.getId() == R.id.sendPush) {
            PushNotification.send(this, getPushContent(), null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getPushContent() {
        return String.format("Путь: %s, Платформа: %s, Маршрут: %s",
            train.getWay(), train.getPlatform(), train.getPath());
    }
}
