package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Страница для отображения списка поездов
 *
 * @author ROMAN PANTELEEV
 */
public class TrainRoutesActivity extends AppCompatActivity {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 5;
    private static final String TRAIN_ROUTE_ID = TrainRoutesActivity.class + ".TRAIN_ROUTE_ID";

    private Client client;
    private TrainRoutesRecyclerAdapter trainRoutesAdapter;
    private SearchTrain searchTrain;

    @InjectView(R.id.startTextView)
    TextView startTextView;

    @InjectView(R.id.endTextView)
    TextView endTextView;

    @InjectView(R.id.dateTextView)
    TextView dateTextView;

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    public static void start(Activity activity, int requestCode, SearchTrain trainRoute) {
        Intent intent = new Intent(activity, TrainRoutesActivity.class);
        intent.putExtra(TrainRoutesActivity.TRAIN_ROUTE_ID, trainRoute);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_routes);
        getSupportActionBar().setTitle("Расписание поездов");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);

        client = ((AndroidApplication) getApplicationContext()).getClient();

        searchTrain = (SearchTrain) getIntent().getSerializableExtra(TRAIN_ROUTE_ID);
        initView();
        loadData(searchTrain);
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.traintRoutesTime);
        trainRoutesAdapter = new TrainRoutesRecyclerAdapter();
        trainRoutesAdapter.setClickListener(new TrainPlaceClickListener());
        trainRoutesAdapter.setItemClickListener(new TrainRouteClickListener());
        recyclerView.setAdapter(trainRoutesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initHeader() {
        startTextView.setText(searchTrain.getArrive().getValue());
        endTextView.setText(searchTrain.getArrival().getValue());
        dateTextView.setText(new DateToStringConverter().convert(searchTrain.getWhen()));
    }

    private void initTrains(List<TrainRoute> trainRoutes) {
        trainRoutesAdapter.setData(trainRoutes);
    }

    private void loadData(SearchTrain trainRoute) {
        client.load(new TrainTimeLoader(trainRoute, new TrainTimeLoadListener(this)));
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
            reference.initHeader();
        }
    }

    private class TrainRouteClickListener implements TrainRoutesRecyclerAdapter.RecyclerViewClickListener {

        @Override
        public void recyclerViewListClicked(View v, int position) {
            TrainRoute trainRoute = trainRoutesAdapter.getItem(position);
            TrainRouteActivity.start(TrainRoutesActivity.this, trainRoute, TRAIN_ROUTE_ACTIVITY_CODE);
        }
    }

    private class TrainPlaceClickListener implements TrainPlaceView.OnPlaceItemClickListener {

        @Override
        public void selectedStantion(Place place) {
            Toast.makeText(TrainRoutesActivity.this, place.getLink(), Toast.LENGTH_SHORT).show();
        }
    }
}
