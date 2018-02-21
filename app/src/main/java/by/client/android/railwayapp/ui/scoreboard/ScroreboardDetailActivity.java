package by.client.android.railwayapp.ui.scoreboard;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
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
 * @author PRV
 */
@EActivity(R.layout.activity_scroreboard_detail)
public class ScroreboardDetailActivity extends AppCompatActivity {

    private static final String TRAIN_KEY = "TRAIN_KEY";

    @ViewById(R.id.icoImageView)
    ImageView icoImageView;

    @ViewById(R.id.trainIdTextView)
    TextView trainIdTextView;

    @ViewById(R.id.wayTextView)
    TextView wayTextView;

    @ViewById(R.id.pathTextView)
    TextView pathTextView;

    @ViewById(R.id.trainTypeTextView)
    TextView trainTypeTextView;

    @ViewById(R.id.platformTextView)
    TextView platformTextView;

    @ViewById(R.id.startTimeTextView)
    TextView startTimeTextView;

    @ViewById(R.id.endTimeTextView)
    TextView endTimeTextView;

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

        trainIdTextView.setText(train.getId());
        icoImageView.setBackgroundResource(new TrainTypeToImage().convert(train.getPathType()));
        trainTypeTextView.setText(train.getTrainType());
        pathTextView.setText(train.getPath());
        wayTextView.setText(train.getWay());
        platformTextView.setText(train.getPlatform());
        startTimeTextView.setText(train.getStart());
        endTimeTextView.setText(train.getEnd());
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
        return String.format(getString(R.string.push_content),
            train.getWay(), train.getPlatform(), train.getPath());
    }
}
