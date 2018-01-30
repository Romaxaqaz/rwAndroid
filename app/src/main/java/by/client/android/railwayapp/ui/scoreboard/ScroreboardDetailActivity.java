package by.client.android.railwayapp.ui.scoreboard;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainRoutesActivity;
import by.client.android.railwayapp.ui.utils.PushNotification;

/**
 * Страница для отображения детальной информации поезда
 *
 * @author Roman Panteleev
 */
public class ScroreboardDetailActivity extends AppCompatActivity {

    private static final int SCOREBOARD_DETAIL_ACTIVITY_CODE = 2;
    private static final String TRAIN_KEY = "TRAIN_KEY";

    @InjectView(R.id.icon)
    ImageView ico;

    @InjectView(R.id.id)
    TextView id;

    @InjectView(R.id.way)
    TextView way;

    @InjectView(R.id.path)
    TextView path;

    @InjectView(R.id.type)
    TextView type;

    @InjectView(R.id.trainPlatform)
    TextView trainPlatform;

    @InjectView(R.id.startTime)
    TextView startTime;

    @InjectView(R.id.endTime)
    TextView endTime;

    private Train train;

    public static void start(Activity activity, Train train, int requestCode) {
        Intent intent = new Intent(activity, ScroreboardDetailActivity.class);
        intent.putExtra(ScroreboardDetailActivity.TRAIN_KEY, train);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroreboard_detail);
        setTitle(getString(R.string.scoreboard_detail_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);

        train = (Train) getIntent().getSerializableExtra(TRAIN_KEY);

        initView(train);
    }

    private void initView(Train train) {
        id.setText(train.getId());
        ico.setBackgroundResource(new TrainTypeToImage().convert(train.getPathType()));
        type.setText(train.getTrainType());
        path.setText(train.getPath());
        way.setText(train.getWay());
        trainPlatform.setText(train.getPlatform());
        startTime.setText(train.getStart());
        endTime.setText(train.getEnd());
    }

    @OnClick(R.id.sendPush)
    void submitButton(View view) {
        if (view.getId() == R.id.sendPush) {
            PushNotification.send(this, getPushContent(), null);
        }
    }

    @OnClick(R.id.trainRoute)
    void trainrouteButton(View view) {
        TrainRouteActivity.start(ScroreboardDetailActivity.this, train, SCOREBOARD_DETAIL_ACTIVITY_CODE);
    }

    @OnClick(R.id.test)
    void testButton(View view) {
        TrainRoutesActivity.start(ScroreboardDetailActivity.this, SCOREBOARD_DETAIL_ACTIVITY_CODE);
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
