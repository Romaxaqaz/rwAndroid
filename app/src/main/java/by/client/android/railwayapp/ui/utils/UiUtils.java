package by.client.android.railwayapp.ui.utils;

import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Утилитный класс для работы с UI
 *
 * @author PRV
 */
public class UiUtils {

    /**
     * Устанавливает видимость view
     *
     * @param visible true - элемент отображается, false - не отображается
     * @param views view для которого/которых необходимо устанвить видимость/невидимость
     */
    public static void setVisibility(boolean visible, View... views) {
        View[] var2 = views;
        int var3 = views.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            View view = var2[var4];
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Sets ListView height dynamically based on the height of the items.
     *
     * @param listView to be resized
     * @return true if the listView is successfully resized, false otherwise
     */
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }

    /**
     * Отображает диалог в режиме fullscreen
     */
    public static void setFullscreenDialog(Dialog dialog) {
        if (dialog == null) {
            throw new NullPointerException("Dialog can not be null");
        }

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setLayout(width, height);
    }
}
