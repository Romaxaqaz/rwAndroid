package by.client.android.railwayapp.ui.scoreboard;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Адаптер для отображения элемента списка поездов
 *
 * @author Roman Panteleev
 */
class TrainAdapter extends BaseListAdapter<Train, BaseHolder<Train>> {

    TrainAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.train_item);
    }

    @Override
    protected TrainAdapter.ViewHolder createHolder(View view) {
        return new TrainAdapter.ViewHolder(view);
    }

    private static class ViewHolder implements BaseHolder<Train> {

        private TextView trainName;
        private TextView trainType;
        private TextView trainTime;
        private TextView trainPath;
        private TextView trainPlatform;


        ViewHolder(View view) {
            trainName = (TextView) view.findViewById(R.id.trainName);
            trainType = (TextView) view.findViewById(R.id.trainType);
            trainTime = (TextView) view.findViewById(R.id.trainTime);
            trainPath = (TextView) view.findViewById(R.id.trainPath);
            trainPlatform = (TextView) view.findViewById(R.id.trainPlatform);
        }

        @Override
        public void bind(Train item) {
            trainName.setText(item.getName());
            trainType.setText(item.getType());
            trainTime.setText(item.getTime());
            trainPath.setText(item.getPath());
            trainPlatform.setText(item.getPlatform());
        }
    }
}