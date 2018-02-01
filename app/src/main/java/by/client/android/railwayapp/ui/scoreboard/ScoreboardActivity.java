package by.client.android.railwayapp.ui.scoreboard;

import java.util.Arrays;
import java.util.List;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница "Виртуальное онлайн-табло" отправки и прибытия поездов
 *
 * @author Roman Panteleev
 */
public class ScoreboardActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int SCOREBOARD_ACTIVITY_CODE = 1;

    @InjectView(R.id.emptyView)
    TextView emptyView;

    private Client client;
    private TrainAdapter trainsAdapter;
    private StantionAdapter stantionAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner stantions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_scoreboard, container, false);
        ButterKnife.inject(view);

        client = ((AndroidApplication) view.getContext().getApplicationContext()).getClient();
        initView(view);
        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        Toolbar toolbar = view.findViewById(R.id.toolBar);
        stantions = toolbar.findViewById(R.id.planets_spinner);

        stantionAdapter = new StantionAdapter(view.getContext());
        stantionAdapter.setData(Arrays.asList(Stantion.values()));
        stantions.setAdapter(stantionAdapter);
        stantions.setOnItemSelectedListener(new StantionClickListener());

        trainsAdapter = new TrainAdapter(view.getContext());
        ListView trainsListView = view.findViewById(R.id.trains);
        trainsListView.setOnItemClickListener(new TrainClickListener());
        trainsListView.setAdapter(trainsAdapter);
    }

    private void loadData(Stantion stantion) {
        client.load(new ScoreboardLoader(stantion, new ScoreboardLoadListener(this)));
    }

    private void initTrains(List<Train> trains) {
        trainsAdapter.setData(trains);
    }

    @Override
    public void onRefresh() {
        loadData((Stantion) stantions.getSelectedItem());
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

    private static class ScoreboardLoadListener extends BaseLoaderListener<ScoreboardActivity, List<Train>> {

        ScoreboardLoadListener(ScoreboardActivity scoreboardActivity) {
            super(scoreboardActivity);
        }

        @Override
        protected void onStart(ScoreboardActivity reference) {
            reference.swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected void onSuccess(ScoreboardActivity reference, List<Train> list) {
            reference.initTrains(list);
        }

        @Override
        protected void onError(ScoreboardActivity reference, Exception exception) {
            // new GlobalExceptionHandler(reference).handle(exception);
        }

        @Override
        protected void onFinish(ScoreboardActivity reference, boolean success) {
            reference.swipeRefreshLayout.setRefreshing(false);
            UiUtils.setVisibility(reference.trainsAdapter.getCount() == 0, reference.emptyView);
        }
    }
}
