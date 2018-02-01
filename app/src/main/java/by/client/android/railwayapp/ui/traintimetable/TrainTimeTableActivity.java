package by.client.android.railwayapp.ui.traintimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import by.client.android.railwayapp.R;

public class TrainTimeTableActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_train_time_table, container, false);

        Button searchStantionButton = view.findViewById(R.id.searchStantionButton);
        searchStantionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchStantionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
