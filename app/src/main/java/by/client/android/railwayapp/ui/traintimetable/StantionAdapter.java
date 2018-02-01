package by.client.android.railwayapp.ui.traintimetable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.api.rw.model.SearchStantion;
import by.client.android.railwayapp.ui.BaseHolder;
import by.client.android.railwayapp.ui.BaseListAdapter;

/**
 * Created by PanteleevRV on 31.01.2018.
 */
class StantionAdapter extends BaseListAdapter<SearchStantion, BaseHolder<SearchStantion>> {

    private Context context;

    StantionAdapter(Context context) {
        super(context);
        this.context = context;
        setItemLayout(R.layout.stantion_item);
    }

    @Override
    protected BaseHolder<SearchStantion> createHolder(View view) {
        return new StantionAdapter.ViewHolder(view);
    }

    private class ViewHolder implements BaseHolder<SearchStantion> {

        private TextView stantionName;

        ViewHolder(View view) {
            stantionName = view.findViewById(R.id.stantionName);
        }

        @Override
        public void bind(SearchStantion stantion) {
            stantionName.setText(stantion.getValue());
        }
    }
}
