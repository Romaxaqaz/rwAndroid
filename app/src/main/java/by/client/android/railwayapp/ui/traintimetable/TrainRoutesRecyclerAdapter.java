package by.client.android.railwayapp.ui.traintimetable;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.routetrain.TrainRoute;
import by.client.android.railwayapp.ui.scoreboard.TrainTypeToImage;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Адаптер для отображения элемента списка поездов
 *
 * <ul>
 *     <li>{@link TrainRoutesRecyclerAdapter#setItemClickListener(RecyclerViewClickListener)}
 *     - устанавливет слушатель нажатия на элемент списка поездов</li>
 *     <li>{@link TrainRoutesRecyclerAdapter#setPlaceItemClickListener(TrainPlaceView.OnPlaceItemClickListener)} -
 *     устанавливает слушатель нажатия на элемент списка мест поезда</li>
 * </ul>
 *
 * @author PRV
 */
class TrainRoutesRecyclerAdapter extends RecyclerView.Adapter<TrainRoutesRecyclerAdapter.ViewHolder> {

    private List<TrainRoute> items = new ArrayList<>();
    private TrainPlaceView.OnPlaceItemClickListener onPlaceItemClickListener;
    private RecyclerViewClickListener recyclerViewClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.traint_time_route_item, parent, false);
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new OnTrainItemClickListener(mViewHolder));
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainRoute train = items.get(position);

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

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<TrainRoute> list) {
        items = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    TrainRoute getItem(int index) {
        return items.get(index);
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

            ectronicRegistration = view.findViewById(R.id.ectronicRegistration);
            corporateTrain = view.findViewById(R.id.corporateTrain);
            expressTrain = view.findViewById(R.id.expressTrain);
        }
    }

    private class OnTrainItemClickListener implements View.OnClickListener {

        private ViewHolder viewHolder;

        OnTrainItemClickListener(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListClicked(view, viewHolder.getLayoutPosition());
        }
    }

    void setPlaceItemClickListener(TrainPlaceView.OnPlaceItemClickListener onPlaceItemClickListener) {
        this.onPlaceItemClickListener = onPlaceItemClickListener;
    }

    void setItemClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    interface RecyclerViewClickListener {

        void recyclerViewListClicked(View v, int position);
    }
}
