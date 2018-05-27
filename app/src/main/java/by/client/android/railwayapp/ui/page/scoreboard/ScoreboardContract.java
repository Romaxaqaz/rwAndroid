package by.client.android.railwayapp.ui.page.scoreboard;

import java.util.List;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.ui.mvp.BasePresenter;
import by.client.android.railwayapp.ui.mvp.BaseView;

public interface ScoreboardContract {

    interface View extends BaseView<ScoreboardContract.Presenter> {

        void setProgressIndicator(boolean active);

        void loadScoreboard(List<Train> articles);

        void loadingError(Exception exception);
    }

    interface Presenter extends BasePresenter {

        void load(ScoreboardStation scoreboardStation);

        void onViewDetached();
    }
}
