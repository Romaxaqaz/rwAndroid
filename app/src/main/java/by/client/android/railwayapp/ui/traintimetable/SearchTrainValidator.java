package by.client.android.railwayapp.ui.traintimetable;

import java.util.Calendar;

import android.content.Context;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.Validator;

/**
 * Created by PanteleevRV on 02.02.2018.
 *
 * @author ROMAN PANTELEEV
 */
public class SearchTrainValidator extends Validator {

    public SearchTrainValidator(Context context) {
        super(context);
    }

    SearchTrain validate(SearchStantion departure, SearchStantion destination, Calendar date) {

        SearchStantion dep = checkNullOrEmpty(departure, R.string.error_required_departure);
        SearchStantion des = checkNullOrEmpty(destination, R.string.error_required_destination);
        Calendar dat = checkNullOrEmpty(date, R.string.error_required_date);

        return isValid() ? new SearchTrain(dep, des, dat.getTime()) : null;
    }


}
