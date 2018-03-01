package by.client.android.railwayapp.ui.news;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerFragment;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.BaseLoaderListener;
import by.client.android.railwayapp.support.Client;
import by.client.android.railwayapp.support.rss.Article;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.utils.UiUtils;

/**
 * Страница для отображения новостей
 *
 * @author Q-RPA
 */
@EFragment(R.layout.activity_news)
public class NewsActivityFragment extends BaseDaggerFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Client client;

    @ViewById(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;

    @ViewById(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter newsAdapter;

    public static NewsActivityFragment newInstance() {
        return new NewsActivityFragment_();
    }

    @AfterViews
    protected void onCreate() {
        initView();

        loadNews();
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(this);

        newsAdapter = new NewsAdapter();
        newsAdapter.setItemClickListener(new NewsItemClickListener());

        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void loadNews() {
        client.send(new NewsLoader(), new NewsLoadListener(this));
    }

    private void initNews(List<Article> news) {
        newsAdapter.setData(news);
    }

    @Override
    public void onRefresh() {
        loadNews();
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    private static class NewsLoadListener extends BaseLoaderListener<NewsActivityFragment, List<Article>> {

        NewsLoadListener(NewsActivityFragment newsActivityFragment) {
            super(newsActivityFragment);
        }

        @Override
        protected void onSuccess(NewsActivityFragment newsActivityFragment, List<Article> articles) {
            newsActivityFragment.initNews(articles);
        }

        @Override
        protected void onStart(NewsActivityFragment newsActivityFragment) {
            newsActivityFragment.swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected void onFinish(NewsActivityFragment newsActivityFragment, boolean success) {
            newsActivityFragment.swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class NewsItemClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        @Override
        public void itemClick(View view, int position) {
            UiUtils.openExternalLink(getContext(), newsAdapter.getItem(position).getLink());
        }
    }
}
