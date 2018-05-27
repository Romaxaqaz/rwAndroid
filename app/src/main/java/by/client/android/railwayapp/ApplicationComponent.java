package by.client.android.railwayapp;

import javax.inject.Singleton;

import by.client.android.railwayapp.ui.base.BaseDaggerActivity;
import by.client.android.railwayapp.ui.page.news.NewsActivityFragment;
import by.client.android.railwayapp.ui.page.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.page.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.page.traintimetable.PlaceInfoActivity;
import by.client.android.railwayapp.ui.page.traintimetable.SearchStationDialog;
import by.client.android.railwayapp.ui.page.traintimetable.TrainRoutesActivity;
import by.client.android.railwayapp.ui.page.traintimetable.TrainTimeTableActivity;
import by.client.android.railwayapp.ui.page.traintimetable.history.TrainRouteHistoryDialog;
import dagger.Component;

/**
 * Основной компонент приложения
 *
 * @author PRV
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApplicationCacheModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    void inject(ScoreboardActivityFragment fragment);

    void inject(TrainRoutesActivity trainRoutesActivity);

    void inject(TrainRouteActivity trainRouteActivity);

    void inject(SearchStationDialog searchStationDialog);

    void inject(PlaceInfoActivity placeInfoActivity);

    void inject(BaseDaggerActivity activity);

    void inject(TrainTimeTableActivity activity);

    void inject(TrainRouteHistoryDialog trainRouteHistoryDialog);

    void inject(NewsActivityFragment newsActivityFragment);
}