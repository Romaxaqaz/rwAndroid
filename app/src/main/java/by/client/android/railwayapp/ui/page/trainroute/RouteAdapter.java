package by.client.android.railwayapp.ui.page.trainroute;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.RouteItem;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;
import by.client.android.railwayapp.ui.utils.DateUtils;

/**
 * Адаптер элементов списка маршрута поезда
 */
class RouteAdapter extends ModifiableRecyclerAdapter<RouteAdapter.ViewHolder, RouteItem> {

    private static final String EMPTY_FIELD_VALUE = "-";

    RouteAdapter() {
        super(R.layout.route_item);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bind(ViewHolder holder, int position) {
        RouteItem item = getItems().get(position);

        holder.route.setText(item.getStation());
        holder.arrival.setText(getDateString(item.getArrival()));
        holder.arrived.setText(getDateString(item.getArrived()));
        holder.stay.setText(item.getStay());
    }

    private String getDateString(Date date) {
        return date != null ? new DateToStringConverter(DateUtils.SHORT_TIME_FORMAT).convert(date) : EMPTY_FIELD_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView route;
        private TextView arrival;
        private TextView arrived;
        private TextView stay;

        ViewHolder(View view) {
            super(view);
            route = view.findViewById(R.id.route);
            arrival = view.findViewById(R.id.departureStation);
            arrived = view.findViewById(R.id.destinationStation);
            stay = view.findViewById(R.id.stay);
        }
    }
}
