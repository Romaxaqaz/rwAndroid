package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.RwService;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Страница для ввода параметров маршрута для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_search_stantion)
public class SearchStantionActivity extends DialogFragment implements SearchView.OnQueryTextListener {

    @ViewById(R.id.searchView)
    SearchView searchView;

    @ViewById(R.id.resultListView)
    ListView resultListView;


    private StantionAdapter stantionAdapter;
    private ChooseStantionDialogListener stantionDialogListener;

    @AfterViews
    void onCreateView() {
        resultListView.setTextFilterEnabled(true);
        stantionAdapter = new StantionAdapter(getActivity());
        resultListView.setAdapter(stantionAdapter);
        resultListView.setOnItemClickListener(new StantionClickListener());

        setupSearchView();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
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

    public interface ChooseStantionDialogListener {

        void selectedStantion(SearchStantion stantion);
    }

    public void setClickListener(ChooseStantionDialogListener stantionDialogListener) {
        this.stantionDialogListener = stantionDialogListener;
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
            stantionDialogListener.selectedStantion(stantion);
            dismiss();
        }
    }
}

