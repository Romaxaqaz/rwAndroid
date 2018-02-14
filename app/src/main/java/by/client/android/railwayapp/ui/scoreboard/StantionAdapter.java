package by.client.android.railwayapp.ui.scoreboard;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.ScoreboardStantion;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Адаптер для отображения станций в выпадающем списке
 *
 * @author PRV
 */
class StantionAdapter extends BaseListAdapter<ScoreboardStantion, BaseHolder<ScoreboardStantion>> {

    StantionAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.dropdown_item);
    }

    private static class ViewHolder implements BaseHolder<ScoreboardStantion> {

        private TextView statusName;

        ViewHolder(View view) {
            statusName = view.findViewById(R.id.stantionNane);
        }

        @Override
        public void bind(ScoreboardStantion scoreboardStantion) {
            statusName.setText(new StantionToNameConverter().convert(scoreboardStantion));
        }
    }

    @Override
    protected ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }
}
