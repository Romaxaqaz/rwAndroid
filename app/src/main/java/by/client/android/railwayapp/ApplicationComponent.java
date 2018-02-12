package by.client.android.railwayapp;

import javax.inject.Singleton;

import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.traintimetable.PlaceInfoActivity;
import by.client.android.railwayapp.ui.traintimetable.SearchStantionActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainRoutesActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainTimeTableActivity;
import by.client.android.railwayapp.ui.traintimetable.history.TrainRouteHistoryFragment;
import dagger.Component;

/**
 * Основной компонент приложения
 *
 * @autor PRV
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApplicationCacheModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication androidApplication);

    void inject(ScoreboardActivityFragment fragment);

    void inject(TrainRoutesActivity trainRoutesActivity);

    void inject(TrainRouteActivity trainRouteActivity);

    void inject(SearchStantionActivity searchStantionActivity);

    void inject(PlaceInfoActivity placeInfoActivity);

    void inject(BaseDaggerActivity activity);

    void inject(TrainTimeTableActivity activity);

    void inject(TrainRouteHistoryFragment trainRouteHistoryFragment);
}