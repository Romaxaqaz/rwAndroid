package by.client.android.railwayapp.ui.traintimetable.history;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Отображает историю запросов
 *
 * @author PRV
 */
@EFragment(R.layout.fragment_train_route_history)
public class TrainRouteHistoryFragment extends DialogFragment {

    @Inject
    ObjectHistory<SearchTrain> trainRouteHistory;

    @ViewById(R.id.resultListView)
    ListView resultListView;

    @ViewById(R.id.clearHistory)
    TextView clearHistory;

    @ViewById(R.id.emptyView)
    TextView emptyView;

    private ChooseRouteDialogListener chooseRouteDialogListener;
    private RouteHistoryAdapter routeHistoryAdapter;

    @AfterViews
    void onCreateView() {
        AndroidApplication.getApp().getApplicationComponent().inject(this);

        routeHistoryAdapter = new RouteHistoryAdapter(getActivity());
        resultListView.setAdapter(routeHistoryAdapter);
        resultListView.setOnItemClickListener(new RouteItemClickListener());

        routeHistoryAdapter.setData(trainRouteHistory.getAll());
        clearHistory.setOnClickListener(new ClearHistoryListener());

        updateEmptyView();
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

    private void updateEmptyView() {
        boolean isEmpty = trainRouteHistory.getAll().size() == 0;
        UiUtils.setVisibility(isEmpty, emptyView);
        UiUtils.setVisibility(!isEmpty, clearHistory);
    }

    public void setClickListener(ChooseRouteDialogListener chooseRouteDialogListener) {
        this.chooseRouteDialogListener = chooseRouteDialogListener;
    }

    private class RouteItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            chooseRouteDialogListener.onSelectedStantion(routeHistoryAdapter.getItem(position));
            dismiss();
        }
    }

    private class ClearHistoryListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            trainRouteHistory.clear();
            routeHistoryAdapter.setData(trainRouteHistory.getAll());
            updateEmptyView();
        }
    }


    /**
     * Callback выбора маршрута
     */
    public interface ChooseRouteDialogListener {

        void onSelectedStantion(SearchTrain searchTrain);
    }
}
