package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.BaseLoaderListener;
import by.client.android.railwayapp.api.Client;
import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.model.Train;

/**
 * Страница "Виртуальное онлайн-табло" отправки и прибытия поездов
 *
 * @author Roman Panteleev
 */
public class ScoreboardActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Client client;
    private TrainAdapter trainsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        setTitle(getString(R.string.scoreboard_header));

        client = ((AndroidApplication) getApplicationContext()).getClient();

        initView();
        loadData();
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        trainsAdapter = new TrainAdapter(this);
        ListView trainsListView = (ListView) findViewById(R.id.trains);
        trainsListView.setAdapter(trainsAdapter);
    }

    private void loadData() {
        client.load(new ScoreboardLoader(new ScoreboardLoadListener(this), Stantion.MINSK));
    }

    private void initTrains(List<Train> list) {
        trainsAdapter.setData(list);
    }

    private void onError() {
        Toast.makeText(this, "Error loading!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        loadData();
    }

    private static class ScoreboardLoadListener extends BaseLoaderListener<ScoreboardActivity, List<Train>> {

        ScoreboardLoadListener(ScoreboardActivity scoreboardActivity) {
            super(scoreboardActivity);
        }

        @Override
        protected void onSuccess(ScoreboardActivity scoreboardActivity, List<Train> list) {
            scoreboardActivity.initTrains(list);
        }

        @Override
        protected void onError(ScoreboardActivity scoreboardActivity, Exception exception) {
            scoreboardActivity.onError();
        }

        @Override
        protected void onFinish(ScoreboardActivity scoreboardActivity, boolean success) {
            scoreboardActivity.swipeRefreshLayout.setRefreshing(false);
        }
    }
}
