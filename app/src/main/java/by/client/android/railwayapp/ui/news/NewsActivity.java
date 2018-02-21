package by.client.android.railwayapp.ui.news;

import java.util.List;

import javax.inject.Inject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import by.client.android.railwayapp.ApplicationComponent;
import by.client.android.railwayapp.BaseDaggerActivity;
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
@EActivity(R.layout.activity_news)
public class NewsActivity extends BaseDaggerActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Client client;

    @ViewById(R.id.newsRecyclerView)
    RecyclerView newsRecyclerView;

    @ViewById(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsAdapter newsAdapter;

    public static void start(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, NewsActivity_.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @AfterViews
    protected void onCreate() {
        initView();

        loadNews();
    }

    @Override
    public void injectActivity(ApplicationComponent component) {
        component.inject(this);
    }

    private void initView() {
        getSupportActionBar().setTitle(R.string.news);

        swipeRefreshLayout.setOnRefreshListener(this);

        newsAdapter = new NewsAdapter();
        newsAdapter.setItemClickListener(new NewsItemClickListener());

        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private static class NewsLoadListener extends BaseLoaderListener<NewsActivity, List<Article>> {

        NewsLoadListener(NewsActivity newsActivity) {
            super(newsActivity);
        }

        @Override
        protected void onSuccess(NewsActivity newsActivity, List<Article> articles) {
            newsActivity.initNews(articles);
        }

        @Override
        protected void onStart(NewsActivity newsActivity) {
            newsActivity.swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected void onFinish(NewsActivity newsActivity, boolean success) {
            newsActivity.swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class NewsItemClickListener implements ModifiableRecyclerAdapter.RecyclerItemsClickListener {

        @Override
        public void itemClick(View view, int position) {
            UiUtils.openExternalLink(getApplicationContext(), newsAdapter.getItem(position).getLink());
        }
    }
}
