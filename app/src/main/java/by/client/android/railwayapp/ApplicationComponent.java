package by.client.android.railwayapp;

import by.client.android.railwayapp.ui.scoreboard.ScoreboardActivityFragment;
import by.client.android.railwayapp.ui.trainroute.TrainRouteActivity;
import by.client.android.railwayapp.ui.traintimetable.TrainRoutesActivity;
import dagger.Component;

/**
 * Основной компонент приложения
 *
 * @autor PRV
 */
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(AndroidApplication myApplication);

    void inject(ScoreboardActivityFragment fragment);

    void inject(TrainRoutesActivity trainRoutesActivity);

    void inject(TrainRouteActivity trainRouteActivity);

    void inject(BaseDaggerActivity activity);
}
