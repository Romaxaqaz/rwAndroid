package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.utils.UiUtils;

public class TrainRouteActivity extends AppCompatActivity {

    private static final String TRAIN_KEY = "TRAIN_ROUTE_KEY";

    private Client client;
    private ProgressBar progressBar;
    private RouteAdapter routeAdapter;

    public static void start(Activity activity, Train train, int requestCode) {
        Intent intent = new Intent(activity, TrainRouteActivity.class);
        intent.putExtra(TRAIN_KEY, train);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);

        client = ((AndroidApplication) getApplicationContext()).getClient();
        Train train = (Train) getIntent().getSerializableExtra(TRAIN_KEY);

        initView();
        loadData("732Б");
    }

    private void initView() {
        routeAdapter = new RouteAdapter(this);
        progressBar = findViewById(R.id.progressBar);
        ListView trainRoute = findViewById(R.id.trainRoute);
        trainRoute.setAdapter(routeAdapter);
    }

    private void loadData(String trainNumber) {
        client.load(new TrainRouteLoader(trainNumber, new TrainRouteLoadListener(this)));
    }

    private static class TrainRouteLoadListener extends BaseLoaderListener<TrainRouteActivity, List<RouteItem>> {

        TrainRouteLoadListener(TrainRouteActivity reference) {
            super(reference);
        }

        @Override
        protected void onStart(TrainRouteActivity reference) {
            UiUtils.setVisibility(true, reference.progressBar);
        }

        @Override
        protected void onSuccess(TrainRouteActivity reference, List<RouteItem> routeItems) {
            reference.routeAdapter.setData(routeItems);
        }

        @Override
        protected void onError(TrainRouteActivity reference, Exception exception) {
            new GlobalExceptionHandler(reference).handle(exception);
        }

        @Override
        protected void onFinish(TrainRouteActivity reference, boolean success) {
            UiUtils.setVisibility(false, reference.progressBar);
        }
    }
}
