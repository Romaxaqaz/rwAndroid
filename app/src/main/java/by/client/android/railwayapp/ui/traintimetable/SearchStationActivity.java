package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.ui.RetrofitCallback;
import by.client.android.railwayapp.ui.utils.UiUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Страница для ввода параметров маршрута для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_search_station)
public class SearchStationActivity extends DialogFragment implements SearchView.OnQueryTextListener {

    @Inject
    RailwayApi railwayApi;

    @Inject
    GlobalExceptionHandler globalExceptionHandler;

    @ViewById(R.id.searchView)
    SearchView searchView;

    @ViewById(R.id.resultListView)
    ListView resultListView;

    @ViewById(R.id.progressBar)
    ProgressBar progressBar;

    private StationAdapter stationAdapter;
    private ChooseStationDialogListener stationDialogListener;

    @AfterViews
    void onCreateView() {
        AndroidApplication.getApp().getApplicationComponent().inject(this);

        resultListView.setTextFilterEnabled(true);
        stationAdapter = new StationAdapter(getActivity());
        resultListView.setAdapter(stationAdapter);
        resultListView.setOnItemClickListener(new StationClickListener());

        setupSearchView();
    }

    @Override
    public void onStart() {
        super.onStart();
        UiUtils.setFullscreenDialog(getDialog());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public void setClickListener(ChooseStationDialogListener stationDialogListener) {
        this.stationDialogListener = stationDialogListener;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        railwayApi.searchStation(newText, "50", "0").enqueue(new SearchResultListener());
        return true;
    }

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint(getString(R.string.input_station_name));
    }

    private void updateListView(List<SearchStation> stations) {
        stationAdapter.setData(stations);
    }

    private class SearchResultListener extends RetrofitCallback<List<SearchStation>> {

        @Override
        public void onStart() {
            UiUtils.setVisibility(true, progressBar);
        }

        @Override
        public void onComplete(Call<List<SearchStation>> call, Response<List<SearchStation>> response) {
            List<SearchStation> body = response.body();
            if (body != null) {
                updateListView(body);
            }
        }

        @Override
        public void onError(Call<List<SearchStation>> call, Throwable throwable) {
            globalExceptionHandler.handle(throwable);
        }

        @Override
        public void onFinish() {
            UiUtils.setVisibility(false, progressBar);
        }
    }

    private class StationClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            SearchStation station = stationAdapter.getItem(position);
            stationDialogListener.selectedStation(station);
            dismiss();
        }
    }

    /**
     * Callback выбора станции
     */
    public interface ChooseStationDialogListener {

        void selectedStation(SearchStation station);
    }
}

