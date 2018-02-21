package by.client.android.railwayapp.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.rss.Article;
import by.client.android.railwayapp.ui.ModifiableRecyclerAdapter;
import by.client.android.railwayapp.ui.converters.DateToStringConverter;

/**
 * Created by PanteleevRV on 21.02.2018.
 *
 * @author PRV
 */
class NewsAdapter extends ModifiableRecyclerAdapter<NewsAdapter.ViewHolder, Article> {

    private static final String NEWS_DATE_FORMAT = "HH:mm dd.MM.yyyy";

    NewsAdapter() {
        super(R.layout.news_item);
    }

    @Override
    public ViewHolder createHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bind(ViewHolder holder, int position) {
        Article article = getItems().get(position);
        holder.newsTitle.setText(article.getTitle());
        holder.newsCategory.setText(article.getCategories());
        holder.newsDate.setText(new DateToStringConverter(NEWS_DATE_FORMAT).convert(article.getPubDate()));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitle;
        private TextView newsCategory;
        private TextView newsDate;

        ViewHolder(View view) {
            super(view);

            newsTitle = view.findViewById(R.id.newsTitle);
            newsCategory = view.findViewById(R.id.newsCategory);
            newsDate = view.findViewById(R.id.newsDate);
        }
    }
}
