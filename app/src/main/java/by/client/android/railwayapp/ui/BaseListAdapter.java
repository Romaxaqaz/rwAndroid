package by.client.android.railwayapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Базовый адаптер списка
 *
 * @author Q-RPA
 */
public abstract class BaseListAdapter<T, ViewHolder extends BaseHolder> extends BaseAdapter {

    private List<T> listData = new ArrayList<>();
    private Context context;
    private int itemResource;

    /**
     * Конструктор класса {@link BaseListAdapter}
     *
     * @param context контекст данных
     */
    public BaseListAdapter(Context context) {
        this.context = context;
    }

    protected void setItemLayout(int itemResource) {
        this.itemResource = itemResource;
    }

    protected abstract ViewHolder createHolder(final View view);

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(itemResource, parent, false);
            holder = createHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.bind(getItem(position));
        return view;
    }

    /**
     * Устанавливает список данных адаптера
     *
     * @param list список данных для отображения
     */
    public void setData(List<T> list) {
        listData = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public T getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
