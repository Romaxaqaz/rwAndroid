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
import android.widget.Toast;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.traintimetable.history.ObjectHistory;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryFragment;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryFragment_;

/**
 * Страница ввода данных для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_train_time_table)
public class TrainTimeTableActivity extends BaseDaggerFragment {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 2;
    private static final String TAG = TrainTimeTableActivity.class.getSimpleName();

    @Inject
    ObjectHistory<SearchTrain> trainRouteHistory;

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
        searchStationButton.setOnClickListener(new SearchClickListenr());
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Click(R.id.history)
    void showHistory() {
        TrainRouteHistoryFragment trainRouteHistoryFragment = new TrainRouteHistoryFragment_();
        trainRouteHistoryFragment.setClickListener(new TrainRouteHistoryFragment.ChooseRouteDialogListener() {
            @Override
            public void onSelectedStation(SearchTrain searchTrain) {
                setArrive(searchTrain.getDepartureStation());
                setArrival(searchTrain.getDestinationStation());
            }
        });
        trainRouteHistoryFragment.show(getFragmentManager(), TAG);
    }


    private class OpenSearchStation implements View.OnClickListener {

        private SearchStationActivity.ChooseStationDialogListener listener;

        private OpenSearchStation(SearchStationActivity.ChooseStationDialogListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            SearchStationActivity searchStationActivity = new SearchStationActivity_();
            searchStationActivity.setClickListener(listener);
            searchStationActivity.show(getFragmentManager(), TAG);
        }
    }

    private class StationArriveSelected implements SearchStationActivity.ChooseStationDialogListener {

        @Override
        public void selectedStation(SearchStation station) {
            setArrive(station);
        }
    }

    private class StationArrivalSelected implements SearchStationActivity.ChooseStationDialogListener {

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

    private class SearchClickListenr implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SearchTrainValidator trainValidator = new SearchTrainValidator(getContext());
            SearchTrain searchTrain = trainValidator.validate(arrive, arrival, currentDate);
            if (searchTrain != null) {
                trainRouteHistory.add(searchTrain);
                TrainRoutesActivity.start(getActivity(), TRAIN_ROUTE_ACTIVITY_CODE, searchTrain);
            } else {
                Toast.makeText(getContext(), join("\n", trainValidator.getErrors()), Toast.LENGTH_LONG).show();
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
}
