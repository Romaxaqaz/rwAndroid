package by.client.android.railwayapp.ui.traintimetable;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.places.Car;
import by.client.android.railwayapp.api.rw.model.places.Tariff;

/**
 * Адаптер для элемента списка информации по вагону
 *
 * @autor PRV
 */
class TariffsRecyclerAdapter extends RecyclerView.Adapter<TariffsRecyclerAdapter.ViewHolder> {

    private List<Tariff> items = new ArrayList<>();
    private Context context;

    TariffsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tariff_item, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<Tariff> list) {
        items = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Tariff tariff = items.get(position);
        holder.tariffTypeTextView.setText(tariff.getType());

        CarsRecyclerAdapter carsRecyclerAdapter = new CarsRecyclerAdapter();
        carsRecyclerAdapter.setData(tariff.getCars());
        carsRecyclerAdapter.setItemClickListener(new CarItemClickListener(tariff, holder));
        holder.carriageRecyclerView.setAdapter(carsRecyclerAdapter);

        holder.tariffTextView.setText(tariff.getPriceByn());
        holder.itemView.setTag(tariff);
    }

    private void updateCarInfo(ViewHolder viewHolder, Car car) {
        viewHolder.totalPlaceTextView.setText(car.getEmptyPlaces().toString());
        viewHolder.upperPlaceTextView.setText(car.getUpperPlaces());
        viewHolder.downPlaceTextView.setText(car.getLowerPlaces());
        viewHolder.upperBPlaceTextView.setText(car.getUpperSidePlaces());
        viewHolder.downBPlaceTextView.setText(car.getLowerSidePlaces());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tariffTypeTextView;
        private RecyclerView carriageRecyclerView;
        private TextView totalPlaceTextView;
        private TextView upperPlaceTextView;
        private TextView downPlaceTextView;
        private TextView upperBPlaceTextView;
        private TextView downBPlaceTextView;
        private TextView tariffTextView;

        ViewHolder(View view) {
            super(view);
            tariffTypeTextView = view.findViewById(R.id.tariffTypeTextView);
            carriageRecyclerView = view.findViewById(R.id.carriageRecyclerView);
            carriageRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            carriageRecyclerView.setItemAnimator(new DefaultItemAnimator());
            totalPlaceTextView = view.findViewById(R.id.totalPlaceTextView);
            upperPlaceTextView = view.findViewById(R.id.upperPlaceTextView);
            downPlaceTextView = view.findViewById(R.id.downPlaceTextView);
            upperBPlaceTextView = view.findViewById(R.id.upperBPlaceTextView);
            downBPlaceTextView = view.findViewById(R.id.downBPlaceTextView);
            tariffTextView = view.findViewById(R.id.tariffTextView);
        }
    }

    private class CarItemClickListener implements CarsRecyclerAdapter.CarViewClickListener {

        private Tariff tariff;
        private ViewHolder viewHolder;

        CarItemClickListener(Tariff tariff, ViewHolder viewHolder) {
            this.tariff = tariff;
            this.viewHolder = viewHolder;
        }

        @Override
        public void onItemClick(View v, int position) {
            updateCarInfo(viewHolder, tariff.getCars().get(position));
        }
    }
}
