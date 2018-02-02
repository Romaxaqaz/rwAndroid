package by.client.android.railwayapp.ui.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by PanteleevRV on 30.01.2018.
 *
 * @author ROMAN PANTELEEV
 */
public class TableBuilder {

    private Context context;
    private static List<View> tableItemView = new ArrayList<>();
    private LinearLayout linearLayout;

    private TableBuilder(Context context) {
        this.context = context;
        linearLayout = new LinearLayout(context);
    }

    public static TableBuilder createTableBuilder(Context context) {
        return new TableBuilder(context);
    }

    public TableBuilder bindRow(String header, String content) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv1 = new TextView(context);
        TextView tv2 = new TextView(context);
        android.widget.TableRow.LayoutParams trparams =
            new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1.setLayoutParams(trparams);
        tv2.setLayoutParams(trparams);
        tv1.setText(header);
        tv2.setText(content);
        TableLayout layoutINNER = new TableLayout(context);
        layoutINNER.setLayoutParams(params);
        TableRow tr = new TableRow(context);
        tr.setLayoutParams(params);
        tr.addView(tv1);
        tr.addView(tv2);
        layoutINNER.addView(tr);
        tableItemView.add(layoutINNER);
        return this;
    }

    public LinearLayout build() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (View item : tableItemView) {
            linearLayout.addView(item);
        }
        return linearLayout;
    }
}
