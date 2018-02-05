package by.client.android.railwayapp.ui.trainroute;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

class RouteAdapter extends BaseListAdapter<RouteItem, BaseHolder<RouteItem>> {

    /**
     * Конструктор класса {@link BaseListAdapter}
     *
     * @param context контекст данных
     */
    RouteAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.route_item);
    }

    @Override
    protected BaseHolder<RouteItem> createHolder(View view) {
        return new RouteAdapter.ViewHolder(view);
    }

    private static class ViewHolder implements BaseHolder<RouteItem> {

        private TextView route;
        private TextView arrival;
        private TextView arrived;
        private TextView travelTime;
        private TextView stay;

        ViewHolder(View view) {
            route = view.findViewById(R.id.route);
            arrival = view.findViewById(R.id.departureStation);
            arrived = view.findViewById(R.id.destinationStantion);
            travelTime = view.findViewById(R.id.travelTime);
            stay = view.findViewById(R.id.stay);
        }

        @Override
        public void bind(RouteItem routeItem) {
            route.setText(routeItem.getStantion());
            arrival.setText(routeItem.getArrival());
            arrived.setText(routeItem.getArrived());
            travelTime.setText(routeItem.getTravelTime());
            stay.setText(routeItem.getStay());
        }
    }
}
