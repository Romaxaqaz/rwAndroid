package by.client.android.railwayapp.ui.page.scoreboard;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.ui.base.BaseHolder;
import by.client.android.railwayapp.ui.base.BaseListAdapter;

/**
 * Адаптер для отображения станций в выпадающем списке
 *
 * @author PRV
 */
class StationAdapter extends BaseListAdapter<ScoreboardStation, BaseHolder<ScoreboardStation>> {

    StationAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.dropdown_item);
    }

    private static class ViewHolder implements BaseHolder<ScoreboardStation> {

        private TextView statusName;

        ViewHolder(View view) {
            statusName = view.findViewById(R.id.stationName);
        }

        @Override
        public void bind(ScoreboardStation scoreboardStation) {
            statusName.setText(new StationToNameConverter().convert(scoreboardStation));
        }
    }

    @Override
    protected ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }
}
