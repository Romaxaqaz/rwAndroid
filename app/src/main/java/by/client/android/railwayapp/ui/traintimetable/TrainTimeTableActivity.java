package by.client.android.railwayapp.ui.traintimetable;

import java.util.Calendar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import static android.text.TextUtils.join;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;

/**
 * Страница ввода данных для поиска поездов
 *
 * @author PRV
 */
@EFragment(R.layout.activity_train_time_table)
public class TrainTimeTableActivity extends Fragment {

    private static final int TRAIN_ROUTE_ACTIVITY_CODE = 2;
    private static final String TAG = TrainTimeTableActivity.class.getSimpleName();

    @ViewById(R.id.departureStation)
    TextView arriveEditText;

    @ViewById(R.id.destinationStantion)
    TextView arrivalEditText;

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.searchStantionButton)
    Button searchStantionButton;

    private SearchStantion arrive;
    private SearchStantion arrival;
    private Calendar currentDate;

    @AfterViews
    void initView() {
        arriveEditText.setOnClickListener(new OpenSearchStantion(new StantionArriveSelected()));
        arrivalEditText.setOnClickListener(new OpenSearchStantion(new StantionArrivalSelected()));
        date.setOnClickListener(new OnDatePickerClickListener());
        searchStantionButton.setOnClickListener(new SearchClickListenr());
    }

    private class OpenSearchStantion implements View.OnClickListener {

        private SearchStantionActivity.ChooseStantionDialogListener listener;

        private OpenSearchStantion(SearchStantionActivity.ChooseStantionDialogListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            SearchStantionActivity searchStantionActivity = new SearchStantionActivity_();
            searchStantionActivity.setClickListener(listener);
            searchStantionActivity.show(getFragmentManager(), TAG);
        }
    }

    private class StantionArriveSelected implements SearchStantionActivity.ChooseStantionDialogListener {

        @Override
        public void selectedStantion(SearchStantion selected) {
            arrive = selected;
            arriveEditText.setText(selected.getValue());
        }
    }

    private class StantionArrivalSelected implements SearchStantionActivity.ChooseStantionDialogListener {

        @Override
        public void selectedStantion(SearchStantion selected) {
            arrival = selected;
            arrivalEditText.setText(selected.getValue());
        }
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
            date.setText(new DateToStringConverter().convert(currentDate.getTime()));
        }
    }
}
