package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.utils.UiUtils;

public class TrainRoutesActivity extends AppCompatActivity {

    private Client client;
    private TrainRoutesAdapter trainRoutesAdapter;
    private ProgressBar progressBar;

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, TrainRoutesActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_routes);

        client = ((AndroidApplication) getApplicationContext()).getClient();

        initView();
        loadData();
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);

        ListView trainRoutesTime = findViewById(R.id.traintRoutesTime);
        trainRoutesAdapter = new TrainRoutesAdapter(this);
        trainRoutesTime.setAdapter(trainRoutesAdapter);
    }

    private void initTrains(List<TrainRoute> trainRoutes) {
        trainRoutesAdapter.setData(trainRoutes);
    }

    private void loadData() {
        client.load(new TrainTimeLoader(null, new TrainTimeLoadListener(this)));
    }

    private static class TrainTimeLoadListener extends BaseLoaderListener<TrainRoutesActivity, List<TrainRoute>> {

        TrainTimeLoadListener(TrainRoutesActivity reference) {
            super(reference);
        }

        @Override
        protected void onStart(TrainRoutesActivity reference) {
            UiUtils.setVisibility(true, reference.progressBar);
        }

        @Override
        protected void onSuccess(TrainRoutesActivity reference, List<TrainRoute> trainRoutes) {
            reference.initTrains(trainRoutes);
        }

        @Override
        protected void onError(TrainRoutesActivity reference, Exception exception) {
            new GlobalExceptionHandler(reference).handle(exception);
        }

        @Override
        protected void onFinish(TrainRoutesActivity reference, boolean success) {
            UiUtils.setVisibility(false, reference.progressBar);
        }
    }
}
