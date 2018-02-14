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
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.ui.RetrofitCallback;
import by.client.android.railwayapp.ui.utils.UiUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Страница для ввода параметров маршрута для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_search_stantion)
public class SearchStantionActivity extends DialogFragment implements SearchView.OnQueryTextListener {

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

    private StantionAdapter stantionAdapter;
    private ChooseStantionDialogListener stantionDialogListener;

    @AfterViews
    void onCreateView() {
        AndroidApplication.getApp().getApplicationComponent().inject(this);

        resultListView.setTextFilterEnabled(true);
        stantionAdapter = new StantionAdapter(getActivity());
        resultListView.setAdapter(stantionAdapter);
        resultListView.setOnItemClickListener(new StantionClickListener());

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

    public void setClickListener(ChooseStantionDialogListener stantionDialogListener) {
        this.stantionDialogListener = stantionDialogListener;
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
        searchView.setQueryHint(getString(R.string.input_stantion_name));
    }

    private void updateListView(List<SearchStantion> stantions) {
        stantionAdapter.setData(stantions);
    }

    private class SearchResultListener extends RetrofitCallback<List<SearchStantion>> {

        @Override
        public void onStart() {
            UiUtils.setVisibility(true, progressBar);
        }

        @Override
        public void onComplete(Call<List<SearchStantion>> call, Response<List<SearchStantion>> response) {
            List<SearchStantion> body = response.body();
            if (body != null) {
                updateListView(body);
            }
        }

        @Override
        public void onError(Call<List<SearchStantion>> call, Throwable throwable) {
            globalExceptionHandler.handle(throwable);
        }

        @Override
        public void onFinish() {
            UiUtils.setVisibility(false, progressBar);
        }
    }

    private class StantionClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            SearchStantion stantion = stantionAdapter.getItem(position);
            stantionDialogListener.selectedStantion(stantion);
            dismiss();
        }
    }

    /**
     * Callback выбора станции
     */
    public interface ChooseStantionDialogListener {

        void selectedStantion(SearchStantion stantion);
    }
}

