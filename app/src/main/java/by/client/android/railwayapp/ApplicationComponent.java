package by.client.android.railwayapp;

import javax.inject.Singleton;

import by.client.android.railwayapp.ui.news.NewsActivityFragment;
import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.traintimetable.PlaceInfoActivity;
import by.client.android.railwayapp.ui.traintimetable.SearchStationDialog;
import by.client.android.railwayapp.ui.traintimetable.TrainRoutesActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainTimeTableActivity;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryDialog;
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