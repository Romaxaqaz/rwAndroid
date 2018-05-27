package by.client.android.railwayapp.ui.page.traintimetable;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.places.Car;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;

/**
 * Created by PanteleevRV on 06.02.2018.
 *
 * @autor PRV
 */
class CarsRecyclerAdapter extends ModifiableRecyclerAdapter<CarsRecyclerAdapter.ViewHolder, Car> {

    public CarsRecyclerAdapter() {
        super(R.layout.train_car_header);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bind(ViewHolder holder, int position) {
        Car car = getItems().get(position);
        holder.carriageNumber.setText(car.getNumber());
        holder.itemView.setTag(car);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView carriageNumber;

        ViewHolder(View view) {
            super(view);
            carriageNumber = view.findViewById(R.id.carriageNumber);
        }
    }
}
