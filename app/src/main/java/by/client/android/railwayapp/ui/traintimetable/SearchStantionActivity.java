package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RwService;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchStantionActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView searchView;
    private ListView resultListView;
    private StantionAdapter stantionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stantion);

        searchView = findViewById(R.id.searchView);
        resultListView = findViewById(R.id.resultListView);
        resultListView.setTextFilterEnabled(true);
        stantionAdapter = new StantionAdapter(this);
        resultListView.setAdapter(stantionAdapter);
        resultListView.setOnItemClickListener(new StantionClickListener());

        setupSearchView();
    }

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("Введите название станции");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        RwService.getInstance().getRwService().searchStantion(newText, "50", "0").enqueue(new SearchResultListener());
        return true;
    }

    private void updateListView(List<SearchStantion> stantions) {
        stantionAdapter.setData(stantions);
    }

    private class SearchResultListener implements Callback<List<SearchStantion>> {

        @Override
        public void onResponse(Call<List<SearchStantion>> call, Response<List<SearchStantion>> response) {
            List<SearchStantion> body = response.body();
            if (body != null) {
                updateListView(body);
            }
        }

        @Override
        public void onFailure(Call<List<SearchStantion>> call, Throwable t) {
        }
    }

    private class StantionClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            SearchStantion stantion = stantionAdapter.getItem(position);
        }
    }
}

