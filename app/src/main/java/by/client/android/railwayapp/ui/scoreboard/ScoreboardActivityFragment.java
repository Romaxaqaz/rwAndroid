package by.client.android.railwayapp.ui.scoreboard;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.support.BaseLoaderListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.settings.SettingsService;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница "Виртуальное онлайн-табло" отправки и прибытия поездов
 *
 * <p>По умолчанию отображается станция "Минск-Пассажирский"</p>
 *
 * @author ROMAN PANTELEEV
 */
@EFragment(R.layout.activity_scoreboard)
public class ScoreboardActivityFragment extends BaseDaggerFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int SCOREBOARD_ACTIVITY_CODE = 1;

    @Inject
    Client client;

    @Inject
    RailwayApi railwayApi;

    @Inject
    SettingsService settingsService;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.emptyView)
    TextView emptyView;

    @ViewById(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.toolBar)
    Toolbar toolbar;

    @ViewById(R.id.trainsListView)
    RecyclerView trainsRecyclerView;

    @ViewById(R.id.stationsSpinner)
    Spinner stationsSpinner;

    @ViewById(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @ViewById(R.id.appBar)
    AppBarLayout appBarLayout;

    private TrainAdapter trainsAdapter;
    private StationAdapter stationAdapter;

    public static ScoreboardActivityFragment newInstance() {
        return new ScoreboardActivityFragment_();
    }

    @AfterViews
    void initFragment() {
        List<ScoreboardStation> scoreboardStations = Arrays.asList(ScoreboardStation.values());

        swipeRefreshLayout.setOnRefreshListener(this);

        trainsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        trainsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trainsRecyclerView.setHasFixedSize(true);

        stationAdapter = new StationAdapter(getActivity());
        stationAdapter.setData(scoreboardStations);
        stationsSpinner.setAdapter(stationAdapter);
        stationsSpinner.setOnItemSelectedListener(new StationClickListener());
        stationsSpinner.setSelection(scoreboardStations.indexOf(settingsService.getScoreboardStation()));

        trainsAdapter = new TrainAdapter(getContext());
        trainsAdapter.setItemClickListener(new TrainClickListener());
        trainsRecyclerView.setAdapter(trainsAdapter);
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onRefresh() {
        loadData(UiUtils.<ScoreboardStation>getSpinnerSelected(stationsSpinner));
    }

    private void loadData(ScoreboardStation scoreboardStation) {
        client.send(new ScoreboardLoader(railwayApi, scoreboardStation), new ScoreboardLoadListener(this));
        settingsService.saveScoreboardStation(scoreboardStation);
    }

    private void initTrains(List<Train> trains) {
        trainsAdapter.setData(trains);
    }

    private class TrainClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        @Override
        public void itemClick(View view, int position) {
            ScoreboardDetailActivity.start(getActivity(), trainsAdapter.getItem(position), SCOREBOARD_ACTIVITY_CODE);
        }
    }

    private class StationClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            loadData(stationAdapter.getItem(position));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    private static class ScoreboardLoadListener extends BaseLoaderListener<ScoreboardActivityFragment, List<Train>> {

        ScoreboardLoadListener(ScoreboardActivityFragment scoreboardActivityFragment) {
            super(scoreboardActivityFragment);
        }

        @Override
        protected void onStart(ScoreboardActivityFragment reference) {
            reference.swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected void onSuccess(ScoreboardActivityFragment reference, List<Train> list) {
            reference.initTrains(list);
        }

        @Override
        protected void onError(ScoreboardActivityFragment reference, Exception exception) {
            reference.globalExceptionHandler.handle(exception);
        }

        @Override
        protected void onFinish(ScoreboardActivityFragment reference, boolean success) {
            reference.swipeRefreshLayout.setRefreshing(false);
            UiUtils.setVisibility(reference.trainsAdapter.getItemCount() > 0, reference.emptyView);
        }
    }
}
