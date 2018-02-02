package by.client.android.railwayapp.ui.scoreboard;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.Stantion;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Адаптер для отображения элементов в выпадающем списке
 *
 * @author ROMAN PANTELEEV
 */
class StantionAdapter extends BaseListAdapter<Stantion, BaseHolder<Stantion>> {

    StantionAdapter(Context context) {
        super(context);
        setItemLayout(R.layout.dropdown_item);
    }

    private static class ViewHolder implements BaseHolder<Stantion> {

        private TextView statusName;

        ViewHolder(View view) {
            statusName = view.findViewById(R.id.stantionNane);
        }

        @Override
        public void bind(Stantion stantion) {
            statusName.setText(new StantionToNameConverter().convert(stantion));
        }
    }

    @Override
    protected ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }
}
