package by.client.android.railwayapp.ui.scoreboard;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.api.ScoreboardStantion;
import by.client.android.railwayapp.model.Train;
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
    SettingsService settingsService;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.emptyView)
    TextView emptyView;

    @ViewById(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @ViewById(R.id.toolBar)
    Toolbar toolbar;

    @ViewById(R.id.trains)
    ListView trainsListView;

    @ViewById(R.id.planets_spinner)
    Spinner stantions;

    private TrainAdapter trainsAdapter;
    private StantionAdapter stantionAdapter;

    @AfterViews
    void initView() {
        List<ScoreboardStantion> scoreboardStantions = Arrays.asList(ScoreboardStantion.values());

        swipeRefreshLayout.setOnRefreshListener(this);
        stantionAdapter = new StantionAdapter(getActivity());
        stantionAdapter.setData(scoreboardStantions);
        stantions.setAdapter(stantionAdapter);
        stantions.setOnItemSelectedListener(new StantionClickListener());
        stantions.setSelection(scoreboardStantions.indexOf(settingsService.getScoreboardStantion()));

        trainsAdapter = new TrainAdapter(getActivity());
        trainsListView.setOnItemClickListener(new TrainClickListener());
        trainsListView.setAdapter(trainsAdapter);
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onRefresh() {
        loadData((ScoreboardStantion) stantions.getSelectedItem());
    }

    private void loadData(ScoreboardStantion scoreboardStantion) {
        client.load(new ScoreboardLoader(scoreboardStantion, new ScoreboardLoadListener(this)));
        settingsService.saveScoreboardStantion(scoreboardStantion);
    }

    private void initTrains(List<Train> trains) {
        trainsAdapter.setData(trains);
    }

    private class TrainClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            ScroreboardDetailActivity.start(getActivity(), trainsAdapter.getItem(position),
                SCOREBOARD_ACTIVITY_CODE);
        }
    }

    private class StantionClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            loadData(stantionAdapter.getItem(position));
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
            UiUtils.setVisibility(reference.trainsAdapter.getCount() == 0, reference.emptyView);
        }
    }
}
