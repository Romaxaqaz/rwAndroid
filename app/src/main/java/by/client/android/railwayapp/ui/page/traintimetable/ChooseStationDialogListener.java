package by.client.android.railwayapp.ui.page.traintimetable;

import by.client.android.railwayapp.api.rw.model.SearchStation;

/**
 * Callback выбора станции
 *
 * @author PRV
 */
public interface ChooseStationDialogListener {

    void selectedStation(SearchStation station);
}
