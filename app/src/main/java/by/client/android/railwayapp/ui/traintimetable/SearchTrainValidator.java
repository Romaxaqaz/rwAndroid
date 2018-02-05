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
 * @author ROMAN PANTELEEV
 */
class SearchTrainValidator extends Validator {

    SearchTrainValidator(Context context) {
        super(context);
    }

    SearchTrain validate(SearchStantion departure, SearchStantion destination, Calendar date) {

        SearchStantion dep = checkNullOrEmpty(departure, R.string.error_required_departure);
        SearchStantion des = checkNullOrEmpty(destination, R.string.error_required_destination);
        Calendar dat = checkNullOrEmpty(date, R.string.error_required_date);

        return isValid() ? new SearchTrain(dep, des, dat.getTime()) : null;
    }
}
