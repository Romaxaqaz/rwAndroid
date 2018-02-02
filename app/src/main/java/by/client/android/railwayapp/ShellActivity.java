package by.client.android.railwayapp;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainTimeTableActivity;

/**
 * Главная страница приложения с отображением меню
 *
 * @author ROMAN PANTELEEV
 */
public class ShellActivity extends AppCompatActivity {

    private static final Map<Integer, Fragment> fragmentMap = new HashMap<>();
    private static final Map<Integer, String> fragmentHeader = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shell);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ButtomMenuListener());

        fragmentMap.put(R.id.action_favorites, new ScoreboardActivity());
        fragmentMap.put(R.id.action_schedules, new TrainTimeTableActivity());

        fragmentHeader.put(R.id.action_favorites, "Виртуальное табло");
        fragmentHeader.put(R.id.action_schedules, "Поиск маршрута");
    }

    private void navigate(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    private class ButtomMenuListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int menuId = item.getItemId();
            if (fragmentMap.containsKey(menuId)) {
                navigate(fragmentMap.get(menuId));
                changeHeader(menuId);
                return true;
            }
            return false;
        }
    }

    private void changeHeader(int menuId) {
        getSupportActionBar().setTitle(fragmentHeader.containsKey(menuId) ? fragmentHeader.get(menuId) : "");
    }
}
