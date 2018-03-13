package by.client.android.railwayapp.ui.traintimetable;

import java.util.Calendar;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import static android.text.TextUtils.join;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.support.database.DataBase;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryDialog;
import by.client.android.railwayapp.ui.utils.DateUtils;
import by.client.android.railwayapp.ui.utils.Dialogs;

/**
 * Страница ввода данных для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_train_time_table)
public class TrainTimeTableActivity extends BaseDaggerFragment {

    @Inject
    DataBase<SearchTrain> trainHistoryDb;

    @ViewById(R.id.departureStation)
    TextView arriveEditText;

    @ViewById(R.id.destinationStation)
    TextView arrivalEditText;

    @ViewById(R.id.date)
    TextView dateTextView;

    @ViewById(R.id.searchStationButton)
    Button searchStationButton;

    @ViewById(R.id.inputCardView)
    CardView inputCardView;

    @ViewById(R.id.root)
    ViewGroup root;

    @ViewById(R.id.history)
    FloatingActionButton floatingActionButton;

    @ViewById(R.id.imageHeader)
    ImageView imageHeader;

    @InstanceState
    SearchStation arrive;

    @InstanceState
    SearchStation arrival;

    @InstanceState
    Calendar currentDate;

    public static TrainTimeTableActivity newInstance() {
        return new TrainTimeTableActivity_();
    }

    @AfterViews
    void initFragment() {
        arriveEditText.setOnClickListener(new OpenSearchStation(new StationArriveSelected()));
        arrivalEditText.setOnClickListener(new OpenSearchStation(new StationArrivalSelected()));
        dateTextView.setOnClickListener(new OnDatePickerClickListener());
        searchStationButton.setOnClickListener(new SearchClickListener());

        setStation(arriveEditText, arrive);
        setStation(arrivalEditText, arrival);
        setDate(currentDate);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("arrive", arrive);
        outState.putParcelable("arrival", arrive);
        outState.putSerializable("currentDate", currentDate);
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
            arrive = station;
            setStation(arriveEditText, station);
        }
    }

    private class StationArrivalSelected implements SearchStationDialog.ChooseStationDialogListener {

        @Override
        public void selectedStation(SearchStation station) {
            arrival = station;
            setStation(arrivalEditText, station);
        }
    }

    private void setStation(TextView textView, SearchStation selected) {
        if (selected != null) {
            textView.setText(selected.getValue());
        }
    }

    private void setDate(Calendar currentDate) {
        if (currentDate != null) {
            dateTextView.setText(new DateToStringConverter().convert(currentDate.getTime()));
        }
    }

    private class OnDatePickerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            DateUtils date = new DateUtils();
            new DatePickerDialog(getActivity(), new DateListener(),
                date.getYear(),
                date.getMonth(),
                date.getDayOfMonth())
                .show();
        }
    }

    private class SearchClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SearchTrainValidator trainValidator = new SearchTrainValidator(getContext());
            SearchTrain searchTrain = trainValidator.validate(arrive, arrival, currentDate);
            if (searchTrain != null) {
                trainHistoryDb.insert(searchTrain);
                TrainRoutesActivity.start(getActivity(), searchTrain);
            } else {
                Dialogs.showToast(getContext(), join("\n", trainValidator.getErrors()));
            }
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            currentDate = DateUtils.createDate()
                .setYear(year)
                .setMonthOfYear(monthOfYear)
                .setDayOfMonth(dayOfMonth)
                .build();
            dateTextView.setText(new DateToStringConverter().convert(currentDate.getTime()));
        }
    }

    private class TrainHistorySelectedListener implements TrainRouteHistoryDialog.ChooseRouteDialogListener {

        @Override
        public void onSelectedStation(SearchTrain searchTrain) {
            arrive = searchTrain.getDepartureStation();
            setStation(arriveEditText, arrive);

            arrival = searchTrain.getDestinationStation();
            setStation(arrivalEditText, arrival);
        }
    }
}
