package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.jetbrains.annotations.NotNull;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.GlobalExceptionHandler;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.support.location.LocationHelper;
import by.client.android.railwayapp.ui.RetrofitCallback;
import by.client.android.railwayapp.ui.utils.Dialogs;
import by.client.android.railwayapp.ui.utils.UiUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Страница для ввода параметров маршрута для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_search_station)
public class SearchStationDialog extends DialogFragment implements SearchView.OnQueryTextListener {

    private static final String TAG = SearchStationDialog.class.getSimpleName();

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

    @ViewById(R.id.location)
    ImageView locationIcon;

    private StationAdapter stationAdapter;
    private ChooseStationDialogListener stationDialogListener;

    public static SearchStationDialog show(@NotNull FragmentManager fragmentManager,
        ChooseStationDialogListener chooseRouteDialogListener) {
        SearchStationDialog searchStationDialog = new SearchStationDialog_();
        searchStationDialog.setClickListener(chooseRouteDialogListener);
        searchStationDialog.show(fragmentManager, TAG);
        return searchStationDialog;
    }

    @AfterViews
    void onCreateView() {
        AndroidApplication.getApp().getApplicationComponent().inject(this);

        stationAdapter = new StationAdapter(getActivity());
        resultListView.setAdapter(stationAdapter);
        resultListView.setTextFilterEnabled(true);
        resultListView.setOnItemClickListener(new StationClickListener());

        locationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationHelper locationHelper = new LocationHelper(getActivity());
                if (locationHelper.isPermission()) {
                    searchView.setQuery(locationHelper.getCity(), false);
                } else {
                    Dialogs.showToast(getContext(), "Разрешите доступ к определению геопозиции и повторите попытку");
                }
            }
        });

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
            stationDialogListener.selectedStation(stationAdapter.getItem(position));
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

