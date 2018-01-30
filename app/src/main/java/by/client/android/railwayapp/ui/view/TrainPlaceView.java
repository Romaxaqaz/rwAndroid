package by.client.android.railwayapp.ui.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.Place;
import by.client.android.railwayapp.ui.traintimetable.TrainPlaceAdapter;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Created by PanteleevRV on 30.01.2018.
 *
 * @author Roman Panteleev
 */
public class TrainPlaceView extends FrameLayout {

    private View rootView;
    private ListView listView;

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
        rootView = inflate(context, R.layout.train_route_places, this);
        listView = rootView.findViewById(R.id.placeListView);
        listView.setScrollContainer(false);
    }

    public void initPlaces(List<Place> places) {
        TrainPlaceAdapter trainPlaceAdapter = new TrainPlaceAdapter(this.getContext());
        trainPlaceAdapter.setData(places);

        listView.setAdapter(trainPlaceAdapter);
        UiUtils.setListViewHeightBasedOnItems(listView);
    }
}
