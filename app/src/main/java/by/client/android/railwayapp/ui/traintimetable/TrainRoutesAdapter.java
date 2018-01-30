package by.client.android.railwayapp.ui.traintimetable;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.TableBuilder;
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Created by PanteleevRV on 19.01.2018.
 *
 * @author Roman Panteleev
 */
class TrainRoutesAdapter extends BaseListAdapter<TrainRoute, BaseHolder<TrainRoute>> {

    private Context context;

    /**
     * Конструктор класса {@link BaseListAdapter}
     *

     * @param context контекст данных
     */
    TrainRoutesAdapter(Context context) {
        super(context);
        this.context = context;
        setItemLayout(R.layout.traint_time_route_item);
    }

    @Override
    protected BaseHolder<TrainRoute> createHolder(View view) {
        return new TrainRoutesAdapter.ViewHolder(view);
    }

    private class ViewHolder implements BaseHolder<TrainRoute> {

        private ImageView icon;
        private TextView id;
        private TextView path;
        private TextView type;
        private TextView arrival;
        private TextView arrived;
        private TextView travelTime;
        private TrainPlaceView placesTest;

        ViewHolder(View view) {
            icon = view.findViewById(R.id.icon);
            id = view.findViewById(R.id.id);
            path = view.findViewById(R.id.path);
            type = view.findViewById(R.id.type);
            arrival = view.findViewById(R.id.arrival);
            arrived = view.findViewById(R.id.arrive);
            travelTime = view.findViewById(R.id.travelTime);
            placesTest = view.findViewById(R.id.placesTest);
        }

        @Override
        public void bind(TrainRoute train) {
            icon.setBackgroundResource(new TrainTypeToImage().convert(train.getIco()));
            id.setText(train.getId());
            path.setText(train.getPath());
            type.setText(train.getTrainType());
            arrival.setText(train.getTrainTime().getArrival());
            arrived.setText(train.getTrainTime().getArrived());
            travelTime.setText(train.getTravelTime());
            placesTest.initPlaces(train.getPlaces());

        }
    }
}
