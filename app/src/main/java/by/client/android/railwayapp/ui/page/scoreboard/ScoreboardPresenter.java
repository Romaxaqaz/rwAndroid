package by.client.android.railwayapp.ui.page.scoreboard;

import java.util.List;
import java.util.concurrent.Callable;

import by.client.android.railwayapp.api.ScoreboardStation;
import by.client.android.railwayapp.api.rw.RailwayApi;
import by.client.android.railwayapp.model.Train;
import by.client.android.railwayapp.support.BaseRxObserverListener;
import by.client.android.railwayapp.ui.mvp.Presenter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ScoreboardPresenter extends Presenter implements ScoreboardContract.Presenter {

    private RailwayApi railwayApi;
    private final ScoreboardContract.View view;

    ScoreboardPresenter(RailwayApi railwayApi, ScoreboardContract.View view) {
        this.railwayApi = railwayApi;
        this.view = view;

        view.setPresenter(this);
        super.onViewAttached();
    }

    @Override
    public void load(ScoreboardStation scoreboardStation) {
        loadScoreboard(scoreboardStation);
    }

    @Override
    public void onViewDetached() {
        super.onViewDetached();
    }

    private void loadScoreboard(ScoreboardStation scoreboardStation) {
        Observable.fromCallable(new ScoreboardLoaderCallable(railwayApi, scoreboardStation))
                .compose(this.applyBinding())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ScoreboardLoadListener(this));
    }

    private class ScoreboardLoaderCallable implements Callable<List<Train>> {

        private RailwayApi railwayApi;
        private ScoreboardStation scoreboardStation;

        ScoreboardLoaderCallable(RailwayApi railwayApi, ScoreboardStation scoreboardStation) {
            this.railwayApi = railwayApi;
            this.scoreboardStation = scoreboardStation;
        }

        @Override
        public List<Train> call() throws Exception {
            return new ScoreboardLoader(railwayApi, scoreboardStation).load();
        }
    }

    private static class ScoreboardLoadListener extends BaseRxObserverListener<ScoreboardPresenter, List<Train>> {

        ScoreboardLoadListener(ScoreboardPresenter reference) {
            super(reference);
        }

        @Override
        protected void onStart(ScoreboardPresenter reference) {
            reference.view.setProgressIndicator(true);
        }

        @Override
        protected void onSuccess(ScoreboardPresenter reference, List<Train> list) {
            reference.view.loadScoreboard(list);
        }

        @Override
        protected void onError(ScoreboardPresenter reference, Exception exception) {
            reference.view.loadingError(exception);
        }

        @Override
        protected void onFinish(ScoreboardPresenter reference) {
            reference.view.setProgressIndicator(false);
        }
    }
}
