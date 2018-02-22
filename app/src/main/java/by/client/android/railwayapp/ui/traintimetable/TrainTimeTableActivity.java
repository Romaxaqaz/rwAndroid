package by.client.android.railwayapp.ui.traintimetable;

import java.util.Calendar;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.DatePickerDialog;
import static android.text.TextUtils.join;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.traintimetable.history.ObjectListHistory;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryDialog;
import by.client.android.railwayapp.ui.utils.Dialogs;

/**
 * Страница ввода данных для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_train_time_table)
public class TrainTimeTableActivity extends BaseDaggerFragment {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 2;

    @Inject
    ObjectListHistory<SearchTrain> trainRouteHistory;

    @ViewById(R.id.departureStation)
    TextView arriveEditText;

    @ViewById(R.id.destinationStation)
    TextView arrivalEditText;

    @ViewById(R.id.date)
    TextView dateTextView;

    @ViewById(R.id.searchStationButton)
    Button searchStationButton;

    private SearchStation arrive;
    private SearchStation arrival;
    private Calendar currentDate;

    @AfterViews
    void initView() {
        arriveEditText.setOnClickListener(new OpenSearchStation(new StationArriveSelected()));
        arrivalEditText.setOnClickListener(new OpenSearchStation(new StationArrivalSelected()));
        dateTextView.setOnClickListener(new OnDatePickerClickListener());
        searchStationButton.setOnClickListener(new SearchClickListener());
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Click(R.id.history)
    void showHistory() {
        TrainRouteHistoryDialog.show(getFragmentManager(), new TrainHistorySelectedListener());
    }

    private class OpenSearchStation implements View.OnClickListener {

        private SearchStationDialog.ChooseStationDialogListener listener;

        private OpenSearchStation(SearchStationDialog.ChooseStationDialogListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            SearchStationDialog.show(getFragmentManager(), listener);
        }
    }

    private class StationArriveSelected implements SearchStationDialog.ChooseStationDialogListener {

        @Override
        public void selectedStation(SearchStation station) {
            setArrive(station);
        }
    }

    private class StationArrivalSelected implements SearchStationDialog.ChooseStationDialogListener {

        @Override
        public void selectedStation(SearchStation station) {
            setArrival(station);
        }
    }

    private void setArrive(SearchStation selected) {
        arrive = selected;
        arriveEditText.setText(selected.getValue());
    }

    private void setArrival(SearchStation selected) {
        arrival = selected;
        arrivalEditText.setText(selected.getValue());
    }

    private class OnDatePickerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Calendar dateAndTime = Calendar.getInstance();
            new DatePickerDialog(getActivity(), new DateListener(), dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
        }
    }

    private class SearchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SearchTrainValidator trainValidator = new SearchTrainValidator(getContext());
            SearchTrain searchTrain = trainValidator.validate(arrive, arrival, currentDate);
            if (searchTrain != null) {
                trainRouteHistory.add(searchTrain);
                TrainRoutesActivity.start(getActivity(), TRAIN_ROUTE_ACTIVITY_CODE, searchTrain);
            } else {
                Dialogs.showToast(getContext(), join("\n", trainValidator.getErrors()));
            }
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            currentDate = Calendar.getInstance();
            currentDate.set(Calendar.YEAR, year);
            currentDate.set(Calendar.MONTH, monthOfYear);
            currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateTextView.setText(new DateToStringConverter().convert(currentDate.getTime()));
        }
    }

    private class TrainHistorySelectedListener implements TrainRouteHistoryDialog.ChooseRouteDialogListener {

        @Override
        public void onSelectedStation(SearchTrain searchTrain) {
            setArrive(searchTrain.getDepartureStation());
            setArrival(searchTrain.getDestinationStation());
        }
    }
}
