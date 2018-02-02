package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;

public class TrainRouteActivity extends AppCompatActivity {

    private static final String TRAIN_KEY = "TRAIN_ROUTE_KEY";

    private Client client;
    private ProgressBar progressBar;
    private RouteAdapter routeAdapter;

    @InjectView(R.id.icon)
    ImageView ico;

    @InjectView(R.id.id)
    TextView id;

    @InjectView(R.id.path)
    TextView path;

    @InjectView(R.id.type)
    TextView type;

    public static void start(Activity activity, TrainRoute train, int requestCode) {
        Intent intent = new Intent(activity, TrainRouteActivity.class);
        intent.putExtra(TRAIN_KEY, train);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_route);

        ButterKnife.inject(this);

        client = ((AndroidApplication) getApplicationContext()).getClient();
        TrainRoute train = (TrainRoute) getIntent().getSerializableExtra(TRAIN_KEY);

        initView();
        loadData(train.getId());
        initHeader(train);
    }

    private void initView() {
        routeAdapter = new RouteAdapter(this);
        progressBar = findViewById(R.id.progressBar);
        ListView trainRoute = findViewById(R.id.trainRoute);
        trainRoute.setAdapter(routeAdapter);
    }

    private void initHeader(TrainRoute trainRoute) {
        id.setText(trainRoute.getId());
        ico.setBackgroundResource(new TrainTypeToImage().convert(trainRoute.getIco()));
        type.setText(trainRoute.getTrainType());
        path.setText(trainRoute.getPath());
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
