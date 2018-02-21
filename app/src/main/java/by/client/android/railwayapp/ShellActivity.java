package by.client.android.railwayapp;

import java.util.HashMap;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import by.client.android.railwayapp.ui.news.NewsActivityFragment_;
import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivityFragment_;
import by.client.android.railwayapp.ui.traintimetable.TrainTimeTableActivity_;

/**
 * Главная страница приложения с отображением меню
 *
 * @author ROMAN PANTELEEV
 */
@EActivity(R.layout.activity_shell)
public class ShellActivity extends BaseDaggerActivity {

    @ViewById(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private static final Map<Integer, Fragment> FRAGMENT_HASH_MAP = new HashMap<>();
    private static final Map<Integer, String> FRAGMENT_HEADER_MAP = new HashMap<>();

    @AfterViews
    void initView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new ButtomMenuListener());

        FRAGMENT_HASH_MAP.put(R.id.action_favorites, new ScoreboardActivityFragment_());
        FRAGMENT_HASH_MAP.put(R.id.action_schedules, new TrainTimeTableActivity_());
        FRAGMENT_HASH_MAP.put(R.id.action_news, new NewsActivityFragment_());

        FRAGMENT_HEADER_MAP.put(R.id.action_favorites, "Виртуальное табло");
        FRAGMENT_HEADER_MAP.put(R.id.action_schedules, "Поиск маршрута");
        FRAGMENT_HEADER_MAP.put(R.id.action_news, "Новости");

        bottomNavigationView.setSelectedItemId(R.id.action_schedules);
    }

    private void navigate(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    private class ButtomMenuListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int menuId = item.getItemId();
            navigate(FRAGMENT_HASH_MAP.get(menuId));
            changeHeader(menuId);
            return true;
        }
    }

    private void changeHeader(int menuId) {
        getSupportActionBar().setTitle(FRAGMENT_HEADER_MAP.containsKey(menuId) ? FRAGMENT_HEADER_MAP.get(menuId) : "");
    }
}
