package by.client.android.railwayapp.ui.page.traintimetable;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.support.BaseRxObserverListener;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.base.BaseDaggerActivity;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.page.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.utils.Dialogs;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.utils.Utils;
import by.client.android.railwayapp.ui.view.TrainPlaceView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Страница для отображения списка поездов по заданному маршруту {@link SearchTrain}
 *
 * @author PRV
 */
@EActivity(R.layout.activity_train_routes)
public class TrainRoutesActivity extends BaseDaggerActivity {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 5;
    private static final String TRAIN_ROUTE_ID = "TRAIN_ROUTE_ID";
    private static final String DATE_FORMAT = "dd MMMM yyyy";

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

    @ViewById(R.id.collapsingInfo)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @ViewById(R.id.appbarLayout)
    AppBarLayout appBarLayout;

    @Extra(TRAIN_ROUTE_ID)
    SearchTrain searchTrain;

    private TrainRoutesRecyclerAdapter trainRoutesAdapter;

    public static void start(@NotNull Activity activity, @NotNull SearchTrain trainRoute) {
        Intent intent = new Intent(activity, TrainRoutesActivity_.class);
        intent.putExtra(TrainRoutesActivity.TRAIN_ROUTE_ID, trainRoute);
        activity.startActivity(intent);
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

        new Handler().postDelayed(this::animate, 500);
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    private void initHeader() {
        startTextView.setText(searchTrain.getDepartureStation().getValue());
        endTextView.setText(searchTrain.getDestinationStation().getValue());
        dateTextView.setText(new DateToStringConverter(DATE_FORMAT).convert(searchTrain.getDepartureDate()));
    }

    private void initTrains(List<TrainRoute> trainRoutes) {
        trainRoutesAdapter.setData(trainRoutes);
    }

    private void animate() {
        TransitionManager.beginDelayedTransition(appBarLayout, new TransitionSet()
                .addTransition(new Fade())
                .addTransition(new ChangeBounds()
                        .setStartDelay(200)
                        .setDuration(400)
                        .setInterpolator(new FastOutSlowInInterpolator())));

        UiUtils.setVisibility(true, collapsingToolbarLayout);
    }

    private void loadData(SearchTrain trainRoute) {
        getRoutes(trainRoute)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new TrainTimeLoadListener(this));
    }

    public Observable<List<TrainRoute>> getRoutes(SearchTrain trainRoute) {
        return Observable.create(observableEmitter ->
        {
            List<TrainRoute> trainRoutes;
            try {
                trainRoutes = new TrainTimeLoader(railwayApi, trainRoute).load();
                observableEmitter.onNext(trainRoutes);

            } catch (Exception e) {
                observableEmitter.onError(e);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }

    private static class TrainTimeLoadListener extends BaseRxObserverListener<TrainRoutesActivity, List<TrainRoute>> {

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
        protected void onFinish(TrainRoutesActivity reference) {
            UiUtils.setVisibility(false, reference.progressBar);
            reference.initHeader();
        }
    }

    private class TrainRouteClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        @Override
        public void itemClick(View view, int position) {
            TrainRoute trainRoute = trainRoutesAdapter.getItem(position);
            TrainRouteActivity.start(TrainRoutesActivity.this, trainRoute, TRAIN_ROUTE_ACTIVITY_CODE);
        }
    }

    private class TrainPlaceClickListener implements TrainPlaceView.OnPlaceItemClickListener {

        @Override
        public void selectedStation(Place place) {
            if (Utils.isBlank(place.getFreePlaces())) {
                Dialogs.showToast(getApplicationContext(), R.string.error_detalization_places);
            } else {
                PlaceInfoActivity.start(TrainRoutesActivity.this, place, TRAIN_ROUTE_ACTIVITY_CODE);
            }
        }
    }
}
