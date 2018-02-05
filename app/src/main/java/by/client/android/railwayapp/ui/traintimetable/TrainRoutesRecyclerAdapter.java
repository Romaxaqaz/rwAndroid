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
import by.client.android.railwayapp.ui.view.TrainPlaceView;

/**
 * Адаптер для отображения элемента списка поездов
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

    public void setData(List<TrainRoute> list) {
        items = new ArrayList<>(list);
        notifyDataSetChanged();
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

        holder.itemView.setTag(train);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    TrainRoute getItem(int index) {
        return items.get(index);
    }

    void setClickListener(TrainPlaceView.OnPlaceItemClickListener onPlaceItemClickListener) {
        this.onPlaceItemClickListener = onPlaceItemClickListener;
    }

    void setItemClickListener(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
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

        ViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            id = view.findViewById(R.id.id);
            path = view.findViewById(R.id.path);
            type = view.findViewById(R.id.type);
            arrival = view.findViewById(R.id.departureStation);
            arrived = view.findViewById(R.id.destinationStantion);
            travelTime = view.findViewById(R.id.travelTime);
            placesTest = view.findViewById(R.id.placesTest);
            placesTest.setClickListener(onPlaceItemClickListener);
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

    interface RecyclerViewClickListener {

        void recyclerViewListClicked(View v, int position);
    }
}
