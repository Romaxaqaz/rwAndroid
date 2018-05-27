package by.client.android.railwayapp.ui.page.news;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.ui.base.BaseDaggerFragment;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.rss.Article;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.utils.Dialogs;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница для отображения новостей
 *
 * @author Q-RPA
 */
@EFragment(R.layout.activity_news)
public class NewsActivityFragment extends BaseDaggerFragment
        implements SwipeRefreshLayout.OnRefreshListener, NewsContract.View {

    public static final String NEWS_URL_KEY = "NEWS_URL_KEY";
    public static final String SAVE_NEWS_STATE = "saveNews";

    @ViewById(R.id.newsRecyclerView)
    ShimmerRecyclerView newsRecyclerView;

    @ViewById(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @InstanceState
    ArrayList<Article> saveNews = new ArrayList<>();

    private NewsContract.Presenter presenter;
    private NewsAdapter newsAdapter;

    public static NewsActivityFragment newInstance() {
        return new NewsActivityFragment_();
    }

    @AfterViews
    protected void onCreate() {
        new NewsPresenter(this);

        initView();

        if (saveNews.isEmpty()) {
            presenter.load();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(this);

        newsAdapter = new NewsAdapter();
        newsAdapter.setData(saveNews);
        newsAdapter.setItemClickListener(new NewsItemClickListener());

        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SAVE_NEWS_STATE, saveNews);
    }

    @Override
    public void onRefresh() {
        presenter.load();
    }

    @Override
    public void setProgressIndicator(boolean active) {
        swipeRefreshLayout.setRefreshing(active);

        if (active) {
            newsRecyclerView.showShimmerAdapter();
        } else {
            newsRecyclerView.hideShimmerAdapter();
        }
    }

    @Override
    public void loadNews(List<Article> articles) {
        initNews(articles);
    }

    @Override
    public void loadingError(Exception exception) {
        //TODO fix error
        Dialogs.showToast(getContext(), exception.getMessage());
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initNews(List<Article> news) {
        saveNews = new ArrayList<>(news);
        newsAdapter.setData(news);
    }

    private class NewsItemClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        @Override
        public void itemClick(View view, int position) {
            UiUtils.openExternalLink(getContext(), newsAdapter.getItem(position).getLink());
        }
    }
}
