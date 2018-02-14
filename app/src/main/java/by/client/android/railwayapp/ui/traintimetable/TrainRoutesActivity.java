package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerActivity;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.support.BaseLoaderListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.utils.Dialogs;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.utils.Utils;
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Страница для отображения списка поездов по заданному маршруту {@link SearchTrain}
 *
 * @author PRV
 */
@EActivity(R.layout.activity_train_routes)
public class TrainRoutesActivity extends BaseDaggerActivity {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 5;
    private static final String TRAIN_ROUTE_ID = "TRAIN_ROUTE_ID";

    @Inject
    Client client;

    @Inject
    RailwayApi railwayApi;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.startTextView)
    TextView startTextView;

    @ViewById(R.id.endTextView)
    TextView endTextView;

    @ViewById(R.id.dateTextView)
    TextView dateTextView;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    @ViewById(R.id.traintRoutesTime)
    RecyclerView recyclerView;

    @ViewById(R.id.emptyView)
    FrameLayout emptyView;

    @Extra(TRAIN_ROUTE_ID)
    SearchTrain searchTrain;

    private TrainRoutesRecyclerAdapter trainRoutesAdapter;

    public static void start(Activity activity, int requestCode, SearchTrain trainRoute) {
        Intent intent = new Intent(activity, TrainRoutesActivity_.class);
        intent.putExtra(TrainRoutesActivity.TRAIN_ROUTE_ID, trainRoute);
        activity.startActivityForResult(intent, requestCode);
    }

    @AfterViews
    void initView() {
        getSupportActionBar().setTitle("Расписание поездов");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        trainRoutesAdapter = new TrainRoutesRecyclerAdapter();
        trainRoutesAdapter.setPlaceItemClickListener(new TrainPlaceClickListener());
        trainRoutesAdapter.setItemClickListener(new TrainRouteClickListener());
        recyclerView.setAdapter(trainRoutesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        loadData(searchTrain);
        initHeader();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    private void initHeader() {
        startTextView.setText(searchTrain.getDepartureStation().getValue());
        endTextView.setText(searchTrain.getDestinationStantion().getValue());
        dateTextView.setText(new DateToStringConverter("dd MMMM yyyy").convert(searchTrain.getDepartureDate()));
    }

    private void initTrains(List<TrainRoute> trainRoutes) {
        trainRoutesAdapter.setData(trainRoutes);
    }

    private void loadData(SearchTrain trainRoute) {
        client.send(new TrainTimeLoader(railwayApi, trainRoute),
            new TrainTimeLoadListener(this));
    }

    private static class TrainTimeLoadListener extends BaseLoaderListener<TrainRoutesActivity, List<TrainRoute>> {

        TrainTimeLoadListener(TrainRoutesActivity reference) {
            super(reference);
        }

        @Override
        protected void onStart(TrainRoutesActivity reference) {
            UiUtils.setVisibility(true, reference.progressBar);
            UiUtils.setVisibility(false, reference.emptyView);
        }

        @Override
        protected void onSuccess(TrainRoutesActivity reference, List<TrainRoute> trainRoutes) {
            reference.initTrains(trainRoutes);
        }

        @Override
        protected void onError(TrainRoutesActivity reference, Exception exception) {
            if (exception instanceof Resources.NotFoundException) {
                UiUtils.setVisibility(true, reference.emptyView);
            } else {
                reference.globalExceptionHandler.handle(exception);
            }
        }

        @Override
        protected void onFinish(TrainRoutesActivity reference, boolean success) {
            UiUtils.setVisibility(false, reference.progressBar);
            reference.initHeader();
        }
    }

    private class TrainRouteClickListener implements TrainRoutesRecyclerAdapter.RecyclerViewClickListener {

        @Override
        public void recyclerViewListClicked(View view, int position) {
            TrainRoute trainRoute = trainRoutesAdapter.getItem(position);
            TrainRouteActivity.start(TrainRoutesActivity.this, trainRoute, TRAIN_ROUTE_ACTIVITY_CODE);
        }
    }

    private class TrainPlaceClickListener implements TrainPlaceView.OnPlaceItemClickListener {

        @Override
        public void selectedStantion(Place place) {
            if (Utils.isBlank(place.getFreePlaces())) {
                Dialogs.showToast(getApplicationContext(), R.string.error_detalization_places);
            } else {
                PlaceInfoActivity.start(TrainRoutesActivity.this, place, TRAIN_ROUTE_ACTIVITY_CODE);
            }
        }
    }
}
