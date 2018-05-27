package by.client.android.railwayapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Map;

import by.client.android.railwayapp.support.common.MapBuilder;
import by.client.android.railwayapp.ui.base.BaseDaggerActivity;
import by.client.android.railwayapp.ui.page.news.NewsActivityFragment;
import by.client.android.railwayapp.ui.page.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.page.traintimetable.TrainTimeTableActivity;

/**
 * Главная страница приложения с отображением меню
 *
 * @author ROMAN PANTELEEV
 */
@EActivity(R.layout.activity_shell)
public class ShellActivity extends BaseDaggerActivity {

    private static final String SELECTED_FRAGMENT_ID = "SELECTED_FRAGMENT_ID";

    private int selectedFragmentId;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            selectedFragmentId = savedInstanceState.getInt(SELECTED_FRAGMENT_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_FRAGMENT_ID, bottomNavigationView.getSelectedItemId());
    }

    @AfterViews
    void initActivity() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new ButtomMenuListener());
        bottomNavigationView.setSelectedItemId(selectedFragmentId != 0 ? selectedFragmentId : R.id.action_schedules);
    }

    private void navigate(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
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
