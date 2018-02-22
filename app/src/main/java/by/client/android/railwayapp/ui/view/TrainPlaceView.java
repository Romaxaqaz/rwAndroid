package by.client.android.railwayapp.ui.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.ui.traintimetable.TrainPlaceAdapter;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * View для отображения доступных мест поезда
 *
 * @author ROMAN PANTELEEV
 */
public class TrainPlaceView extends FrameLayout {

    private ListView listView;
    private TextView emptyView;
    private TrainPlaceAdapter trainPlaceAdapter;
    private OnPlaceItemClickListener onPlaceItemClickListener;

    public TrainPlaceView(Context context) {
        super(context);
        init(context);
    }

    public TrainPlaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TrainPlaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = inflate(context, R.layout.train_route_places, this);
        listView = rootView.findViewById(R.id.placeListView);
        emptyView = rootView.findViewById(R.id.emptyView);
        listView.setScrollContainer(false);
    }

    public void initPlaces(List<Place> places) {
        trainPlaceAdapter = new TrainPlaceAdapter(this.getContext());
        trainPlaceAdapter.setData(places);

        listView.setAdapter(trainPlaceAdapter);
        listView.setOnItemClickListener(new PlaceItemClickListener());

        UiUtils.setListViewHeightBasedOnItems(listView);
        UiUtils.setVisibility(places.isEmpty(), emptyView);
    }

    public void setClickListener(OnPlaceItemClickListener onPlaceItemClickListener) {
        this.onPlaceItemClickListener = onPlaceItemClickListener;
    }

    private class PlaceItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            onPlaceItemClickListener.selectedStation(trainPlaceAdapter.getItem(position));
        }
    }

    /**
     * Callback выбора категории мест для отображения
     */
    public interface OnPlaceItemClickListener {

        void selectedStation(Place place);
    }
}
