package by.client.android.railwayapp.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Базовый адаптер для {@link RecyclerView} c обработкой нажатия на элемент списка.
 *
 * @param <T> класс viewHolder
 * @param <Param> тип элементов списка
 *
 * @author PRV
 */
public abstract class ModifiableRecyclerAdapter<T extends RecyclerView.ViewHolder, Param> extends RecyclerView.Adapter<T> {

    private RecyclerItemsClickListener itemClickListener;
    private List<Param> items = new ArrayList<>();
    private int itemResource;

    public ModifiableRecyclerAdapter(int itemResource) {
        this.itemResource = itemResource;
    }

    public abstract T createHolder(View view);

    public abstract void bind(T holder, int position);

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemResource, parent, false);
        final T mViewHolder = createHolder(view);
        view.setOnClickListener(new OnNewsItemClickListener(mViewHolder));
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Param> list) {
        items = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    protected List<Param> getItems() {
        return items;
    }

    public Param getItem(int index) {
        return items.get(index);
    }

    private class OnNewsItemClickListener implements View.OnClickListener {

        private T viewHolder;

        OnNewsItemClickListener(T viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.itemClick(view, viewHolder.getLayoutPosition());
        }
    }

    public void setItemClickListener(RecyclerItemsClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface RecyclerItemsClickListener {

        void itemClick(View v, int position);
    }
}
