package by.client.android.railwayapp.ui.traintimetable.history;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.SearchTrain;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Адаптер для отображения элементов списка истории запросов
 *
 * @author PRV
 */
class RouteHistoryAdapter extends BaseListAdapter<SearchTrain, BaseHolder<SearchTrain>> {

    RouteHistoryAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.history_route_item);
    }

    @Override
    protected BaseHolder<SearchTrain> createHolder(View view) {
        return new ViewHolder(view);
    }

    private class ViewHolder implements BaseHolder<SearchTrain> {

        private TextView departureStation;
        private TextView destinationStation;

        ViewHolder(View view) {
            departureStation = view.findViewById(R.id.departureStation);
            destinationStation = view.findViewById(R.id.destinationStation);
        }

        @Override
        public void bind(SearchTrain searchTrain) {
            departureStation.setText(searchTrain.getDepartureStation().getValue());
            destinationStation.setText(searchTrain.getDestinationStation().getValue());
        }
    }
}