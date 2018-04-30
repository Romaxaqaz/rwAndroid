package by.client.android.railwayapp.ui.traintimetable;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.places.Car;

/**
 * Created by PanteleevRV on 06.02.2018.
 *
 * @autor PRV
 */
class CarsRecyclerAdapter extends RecyclerView.Adapter<CarsRecyclerAdapter.ViewHolder> {

    private List<Car> items = new ArrayList<>();
    private CarViewClickListener carViewClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_car_header, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new OnCarItemClickListener(viewHolder));
        return viewHolder;
    }

    public void setData(List<Car> list) {
        items = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    void setItemClickListener(CarViewClickListener carViewClickListener) {
        this.carViewClickListener = carViewClickListener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = items.get(position);
        holder.carriageNumber.setText(car.getNumber());
        holder.itemView.setTag(car);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView carriageNumber;

        ViewHolder(View view) {
            super(view);
            carriageNumber = view.findViewById(R.id.carriageNumber);
        }
    }

    private class OnCarItemClickListener implements View.OnClickListener {

        private ViewHolder viewHolder;

        OnCarItemClickListener(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            carViewClickListener.onItemClick(view, viewHolder.getLayoutPosition());
        }
    }

    interface CarViewClickListener {

        void onItemClick(View v, int position);
    }
}
