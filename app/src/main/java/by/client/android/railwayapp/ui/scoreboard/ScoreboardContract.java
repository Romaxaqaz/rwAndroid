package by.client.android.railwayapp.ui.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.BasePresenter;
import by.client.android.railwayapp.ui.BaseView;

public interface ScoreboardContract {

    interface View extends BaseView<ScoreboardContract.Presenter> {

        void setProgressIndicator(boolean active);

        void loadScoreboard(List<Train> articles);

        void loadingError(Exception exception);
    }

    interface Presenter extends BasePresenter {

        void load(ScoreboardStation scoreboardStation);
    }
}
