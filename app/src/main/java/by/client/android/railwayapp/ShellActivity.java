package by.client.android.railwayapp;

import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import by.client.android.railwayapp.support.common.MapBuilder;
import by.client.android.railwayapp.ui.news.NewsActivityFragment;
import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.traintimetable.TrainTimeTableActivity;

/**
 * Главная страница приложения с отображением меню
 *
 * @author ROMAN PANTELEEV
 */
@EActivity(R.layout.activity_shell)
public class ShellActivity extends BaseDaggerActivity {

    @ViewById(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private static final Map<Integer, Fragment> FRAGMENT_HASH_MAP = new MapBuilder<Integer, Fragment>()
        .put(R.id.action_favorites, ScoreboardActivityFragment.newInstance())
        .put(R.id.action_schedules, TrainTimeTableActivity.newInstance())
        .put(R.id.action_news, NewsActivityFragment.newInstance())
        .build();

    private static final Map<Integer, String> FRAGMENT_HEADER_MAP = new MapBuilder<Integer, String>()
        .put(R.id.action_favorites, "Виртуальное табло")
        .put(R.id.action_schedules, "Поиск маршрута")
        .put(R.id.action_news, "Новости")
        .build();

    @AfterViews
    void initActivity() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new ButtomMenuListener());
        bottomNavigationView.setSelectedItemId(R.id.action_schedules);
    }

    private void navigate(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
            //.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right)
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(fragment.getClass().getName())
            .commit();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            appExit();
        } else {
            super.onBackPressed();
        }
    }

    private void changeHeader(int menuId) {
        getSupportActionBar().setTitle(FRAGMENT_HEADER_MAP.containsKey(menuId)
            ? FRAGMENT_HEADER_MAP.get(menuId)
            : getResources().getString(R.string.app_name));
    }

    private void appExit() {
        this.finish();
        System.exit(0);
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
}
