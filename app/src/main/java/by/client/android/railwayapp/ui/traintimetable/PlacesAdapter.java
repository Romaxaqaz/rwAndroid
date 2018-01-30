package by.client.android.railwayapp.ui.traintimetable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Created by PanteleevRV on 19.01.2018.
 *
 * @author Roman Panteleev
 */
class PlacesAdapter extends BaseListAdapter<Place, BaseHolder<Place>> {

    PlacesAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.place_item);
    }

    @Override
    protected BaseHolder<Place> createHolder(View view) {
        return new ViewHolder(view);
    }

    private static class ViewHolder implements BaseHolder<Place> {

        private TextView placeName;
        private TextView placeCost;
        private TextView placeCount;

        ViewHolder(View view) {
            placeName = view.findViewById(R.id.placeName);
            placeCost = view.findViewById(R.id.placeCost);
            placeCount = view.findViewById(R.id.placeCount);
        }

        @Override
        public void bind(Place place) {
            placeName.setText(place.getName());
            placeCost.setText(place.getPrice());
            placeCount.setText(place.getFreePlaces());
        }
    }
}
