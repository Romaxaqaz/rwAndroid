package by.client.android.railwayapp.ui.traintimetable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStation;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Адаптер для отображения станций марщрута
 *
 * @author PRV
 */
class StationAdapter extends BaseListAdapter<SearchStation, BaseHolder<SearchStation>> {

    StationAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.station_item);
    }

    @Override
    protected BaseHolder<SearchStation> createHolder(View view) {
        return new StationAdapter.ViewHolder(view);
    }

    private class ViewHolder implements BaseHolder<SearchStation> {

        private TextView stationName;

        ViewHolder(View view) {
            stationName = view.findViewById(R.id.stationName);
        }

        @Override
        public void bind(SearchStation station) {
            stationName.setText(station.getValue());
        }
    }
}
