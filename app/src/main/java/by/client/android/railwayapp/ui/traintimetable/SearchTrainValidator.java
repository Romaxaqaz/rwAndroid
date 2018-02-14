package by.client.android.railwayapp.ui.traintimetable;

import java.util.Calendar;

import android.content.Context;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.Validator;

/**
 * Валидатор модели {@link SearchTrain}
 *
 * @author PRV
 */
class SearchTrainValidator extends Validator {

    SearchTrainValidator(Context context) {
        super(context);
    }

    SearchTrain validate(SearchStantion departure, SearchStantion destination, Calendar date) {

        SearchStantion departureStantion = checkNullOrEmpty(departure, R.string.error_required_departure);
        SearchStantion destinationStantion = checkNullOrEmpty(destination, R.string.error_required_destination);
        Calendar calendar = checkNullOrEmpty(date, R.string.error_required_date);

        return isValid() ? new SearchTrain(departureStantion, destinationStantion, calendar.getTime()) : null;
    }
}
