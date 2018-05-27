package by.client.android.railwayapp.ui.page.traintimetable;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.places.Car;
import by.client.android.railwayapp.api.rw.model.places.Tariff;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.utils.UiUtils;
import by.client.android.railwayapp.ui.utils.Utils;

import static android.text.TextUtils.join;

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
        View view = UiUtils.inflate(parent, R.layout.tariff_item);
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
        holder.tariffTextView.setText(tariff.getPrice());
        holder.itemView.setTag(tariff);
    }

    private void updateCarInfo(ViewHolder viewHolder, Car car) {
        viewHolder.totalPlaceTextView.setText(join(", ", car.getEmptyPlaces()));
        hideView(car.getLowerPlaces(), viewHolder.downPlaceView, viewHolder.downPlaceTextView);
        hideView(car.getUpperPlaces(), viewHolder.upperPlaceView, viewHolder.upperPlaceTextView);
        hideView(car.getUpperSidePlaces(), viewHolder.upperBPlaceView, viewHolder.upperBPlaceTextView);
        hideView(car.getLowerSidePlaces(), viewHolder.downBPlaceView, viewHolder.downBPlaceTextView);
    }

    private void hideView(String content, LinearLayout linearLayout, TextView textView) {
        textView.setText(content);
        UiUtils.setVisibility(!Utils.isBlank(content), linearLayout);
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

        private LinearLayout downPlaceView;
        private LinearLayout upperPlaceView;
        private LinearLayout upperBPlaceView;
        private LinearLayout downBPlaceView;

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

            downPlaceView = view.findViewById(R.id.downPlaceView);
            upperPlaceView = view.findViewById(R.id.upperPlaceView);
            upperBPlaceView = view.findViewById(R.id.upperBPlaceView);
            downBPlaceView = view.findViewById(R.id.downBPlaceView);
        }
    }

    private class CarItemClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        private Tariff tariff;
        private ViewHolder viewHolder;

        CarItemClickListener(Tariff tariff, ViewHolder viewHolder) {
            this.tariff = tariff;
            this.viewHolder = viewHolder;
        }

        @Override
        public void itemClick(View view, int position) {
            updateCarInfo(viewHolder, tariff.getCars().get(position));
        }
    }
}
