package by.client.android.railwayapp.ui.page.traintimetable;

import android.content.Context;

import java.util.Calendar;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStation;
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

    SearchTrain validate(SearchStation departure, SearchStation destination, Calendar date) {

        SearchStation departureStation = checkNullOrEmpty(departure, R.string.error_required_departure);
        SearchStation destinationStation = checkNullOrEmpty(destination, R.string.error_required_destination);
        Calendar calendar = checkNullOrEmpty(date, R.string.error_required_date);

        return isValid() ? new SearchTrain(departureStation, destinationStation, calendar.getTime()) : null;
    }
}
