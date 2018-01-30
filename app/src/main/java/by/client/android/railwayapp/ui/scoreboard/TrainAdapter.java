package by.client.android.railwayapp.ui.scoreboard;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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

        private ImageView icon;
        private TextView id;
        private TextView path;
        private TextView type;
        private TextView way;
        private TextView platform;
        private TextView start;
        private TextView end;

        ViewHolder(View view) {
            icon = view.findViewById(R.id.icon);
            id = view.findViewById(R.id.id);
            path = view.findViewById(R.id.path);
            type = view.findViewById(R.id.type);
            way = view.findViewById(R.id.way);
            platform = view.findViewById(R.id.paltform);
            start = view.findViewById(R.id.start);
            end = view.findViewById(R.id.end);
        }

        @Override
        public void bind(Train train) {
            icon.setBackgroundResource(new TrainTypeToImage().convert(train.getPathType()));
            id.setText(train.getId());
            path.setText(train.getPath());
            type.setText(train.getTrainType());
            way.setText(train.getWay());
            platform.setText(train.getPlatform());
            start.setText(train.getStart());
            end.setText(train.getEnd());
        }
    }
}