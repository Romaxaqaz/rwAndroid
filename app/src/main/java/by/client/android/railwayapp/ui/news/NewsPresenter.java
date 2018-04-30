package by.client.android.railwayapp.ui.news;

import java.util.List;
import java.util.concurrent.Callable;

import by.client.android.railwayapp.support.BaseRxObserverListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.support.rss.Article;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter for the news page
 *
 * @author RPA
 */
public class NewsPresenter implements NewsContract.Presenter {

    private Client client;
    private final NewsContract.View view;

    NewsPresenter(Client client, NewsContract.View view) {
        this.client = client;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        Observable.fromCallable(new NewsLoaderCallable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new NewsLoaderListener(this));
    }

    private class NewsLoaderCallable implements Callable<List<Article>> {

        @Override
        public List<Article> call() {
            return new NewsLoader().load();
        }
    }

    private static class NewsLoaderListener extends BaseRxObserverListener<NewsPresenter, List<Article>> {

        NewsLoaderListener(NewsPresenter newsPresenter) {
            super(newsPresenter);
        }

        @Override
        protected void onStart(NewsPresenter newsPresenter) {
            newsPresenter.view.setProgressIndicator(true);
        }

        @Override
        protected void onSuccess(NewsPresenter newsPresenter, List<Article> articles) {
            newsPresenter.view.loadNews(articles);
        }

        @Override
        protected void onError(NewsPresenter newsPresenter, Exception exception) {
            newsPresenter.view.loadingError(exception);
        }

        @Override
        protected void onFinish(NewsPresenter newsPresenter) {
            newsPresenter.view.setProgressIndicator(false);
        }
    }
}
