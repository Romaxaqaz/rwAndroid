package by.client.android.railwayapp.ui.news;

import java.util.List;

import by.client.android.railwayapp.support.rss.Article;
import by.client.android.railwayapp.ui.BasePresenter;
import by.client.android.railwayapp.ui.BaseView;

/**
 * Contract for the news page
 *
 * @author RPA
 */
public interface NewsContract {

    interface View extends BaseView<Presenter> {

        void setProgressIndicator(boolean active);

        void loadNews(List<Article> articles);

        void loadingError(Exception exception);
    }

    interface Presenter extends BasePresenter {

    }
}
