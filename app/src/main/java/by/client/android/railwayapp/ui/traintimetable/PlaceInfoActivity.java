package by.client.android.railwayapp.ui.traintimetable;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerActivity;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.api.rw.model.places.TrainPlaceInfo;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.ui.RetrofitCallback;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Странице отображения свободных мест поезда
 *
 * @author PRV
 */
@EActivity(R.layout.activity_place_info)
public class PlaceInfoActivity extends BaseDaggerActivity {

    private static final String PLACE_KEY = "PLACE_KEY";

    @Inject
    RailwayApi railwayApi;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @Extra(PLACE_KEY)
    Place place;

    @ViewById(R.id.iconImageView)
    ImageView iconImageView;

    @ViewById(R.id.trainIdTextView)
    TextView trainIdTextView;

    @ViewById(R.id.pathTextView)
    TextView pathTextView;

    @ViewById(R.id.typeTextView)
    TextView typeTextView;

    @ViewById(R.id.passengerRouteTextView)
    TextView passengerRouteTextView;

    @ViewById(R.id.departureTextView)
    TextView departureTextView;

    @ViewById(R.id.arrivalTextView)
    TextView arrivalTextView;

    @ViewById(R.id.travelTimeTextView)
    TextView travelTimeTextView;

    @ViewById(R.id.tariffsRecyclerView)
    RecyclerView tariffsRecyclerView;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    private TariffsRecyclerAdapter tariffsRecyclerAdapter;

    public static void start(Activity activity, Place place, int requestCode) {
        Intent intent = new Intent(activity, PlaceInfoActivity_.class);
        intent.putExtra(PLACE_KEY, place);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    @AfterViews
    void initActivity() {
        initView();
        loadPlaceInfo();
    }

    private void initView() {
        getSupportActionBar().setTitle("Свободные места");

        tariffsRecyclerAdapter = new TariffsRecyclerAdapter(this);
        tariffsRecyclerView.setAdapter(tariffsRecyclerAdapter);
        tariffsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tariffsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void bind(TrainPlaceInfo trainPlaceInfo) {
        trainIdTextView.setText(trainPlaceInfo.getTrainNumber());
        iconImageView.setBackgroundResource(new TrainTypeToImage().convertIfContain(trainPlaceInfo.getTrainType()));
        pathTextView.setText(trainPlaceInfo.getPath());
        typeTextView.setText(trainPlaceInfo.getTrainType());
        passengerRouteTextView.setText(trainPlaceInfo.getUserRoute());
        departureTextView.setText(trainPlaceInfo.getRouteStartDate());
        arrivalTextView.setText(trainPlaceInfo.getRouteEndDate());
        travelTimeTextView.setText(trainPlaceInfo.getRoute().getTimeInWay());
        tariffsRecyclerAdapter.setData(trainPlaceInfo.getTariffs());
    }

    private void loadPlaceInfo() {
        railwayApi.getPlacesInfo(place.getLink()).enqueue(new PlaceLoaderListener());
    }

    private class PlaceLoaderListener extends RetrofitCallback<TrainPlaceInfo> {

        @Override
        public void onStart() {
            UiUtils.setVisibility(true, progressBar);
        }

        @Override
        public void onComplete(Call<TrainPlaceInfo> call, Response<TrainPlaceInfo> response) {
            bind(response.body());
        }

        @Override
        public void onError(Call<TrainPlaceInfo> call, Throwable throwable) {
            globalExceptionHandler.handle(throwable);
        }

        @Override
        public void onFinish() {
            UiUtils.setVisibility(false, progressBar);
        }
    }
}
