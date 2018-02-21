package by.client.android.railwayapp.ui.traintimetable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Адаптер для отображения элемента списка поездов
 *
 * @author PRV
 */
class TrainRoutesRecyclerAdapter extends ModifiableRecyclerAdapter<TrainRoutesRecyclerAdapter.ViewHolder, TrainRoute> {

    private TrainPlaceView.OnPlaceItemClickListener onPlaceItemClickListener;

    TrainRoutesRecyclerAdapter() {
        super(R.layout.traint_time_route_item);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bind(ViewHolder holder, int position) {
        TrainRoute train = getItems().get(position);

        holder.icon.setBackgroundResource(new TrainTypeToImage().convert(train.getIco()));
        holder.id.setText(train.getId());
        holder.path.setText(train.getPath());
        holder.type.setText(train.getTrainType());
        holder.arrival.setText(train.getTrainTime().getArrival());
        holder.arrived.setText(train.getTrainTime().getArrived());
        holder.travelTime.setText(train.getTravelTime());
        holder.placesTest.initPlaces(train.getPlaces());

        UiUtils.setVisibility(train.getParameters().getElectronicRegistration(), holder.ectronicRegistration);
        UiUtils.setVisibility(train.getParameters().getCorporateTrain(), holder.corporateTrain);
        UiUtils.setVisibility(train.getParameters().getExpressTrain(), holder.expressTrain);

        holder.itemView.setTag(train);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView id;
        private TextView path;
        private TextView type;
        private TextView arrival;
        private TextView arrived;
        private TextView travelTime;
        private TrainPlaceView placesTest;
        private TextView ectronicRegistration;
        private TextView corporateTrain;
        private TextView expressTrain;

        ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            id = view.findViewById(R.id.id);
            path = view.findViewById(R.id.path);
            type = view.findViewById(R.id.type);
            arrival = view.findViewById(R.id.departureStation);
            arrived = view.findViewById(R.id.destinationStation);
            travelTime = view.findViewById(R.id.travelTime);
            placesTest = view.findViewById(R.id.placesTest);
            placesTest.setClickListener(onPlaceItemClickListener);

            ectronicRegistration = view.findViewById(R.id.electronicRegistration);
            corporateTrain = view.findViewById(R.id.corporateTrain);
            expressTrain = view.findViewById(R.id.expressTrain);
        }
    }

    void setPlaceItemClickListener(TrainPlaceView.OnPlaceItemClickListener onPlaceItemClickListener) {
        this.onPlaceItemClickListener = onPlaceItemClickListener;
    }
}
