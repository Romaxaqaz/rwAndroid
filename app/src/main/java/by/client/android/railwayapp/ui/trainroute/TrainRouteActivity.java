package by.client.android.railwayapp.ui.trainroute;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerActivity;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.BaseLoaderListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница отображения маршрута позда
 *
 * @author PRV
 */
@EActivity(R.layout.activity_train_route)
public class TrainRouteActivity extends BaseDaggerActivity {

    private static final String TRAIN_KEY = "TRAIN_ROUTE_KEY";

    @Inject
    Client client;

    @Inject
    RailwayApi railwayApi;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.icon)
    ImageView ico;

    @ViewById(R.id.id)
    TextView id;

    @ViewById(R.id.path)
    TextView path;

    @ViewById(R.id.type)
    TextView type;

    @ViewById(R.id.trainRoute)
    ListView trainRoute;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    @Extra(TRAIN_KEY)
    TrainRoute train;

    private RouteAdapter routeAdapter;

    public static void start(Activity activity, TrainRoute train, int requestCode) {
        Intent intent = new Intent(activity, TrainRouteActivity_.class);
        intent.putExtra(TRAIN_KEY, train);
        activity.startActivityForResult(intent, requestCode);
    }

    @AfterViews
    void initActivity() {
        getSupportActionBar().setTitle(R.string.train_route);

        routeAdapter = new RouteAdapter(this);
        trainRoute.setAdapter(routeAdapter);

        loadData();
        initHeader();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    private void initHeader() {
        id.setText(train.getId());
        ico.setBackgroundResource(new TrainTypeToImage().convert(train.getIco()));
        type.setText(train.getTrainType());
        path.setText(train.getPath());
    }

    private void loadData() {
        client.load(new TrainRouteLoader(railwayApi, train.getId(), new TrainRouteLoadListener(this)));
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
            reference.globalExceptionHandler.handle(exception);
        }

        @Override
        protected void onFinish(TrainRouteActivity reference, boolean success) {
            UiUtils.setVisibility(false, reference.progressBar);
        }
    }
}
