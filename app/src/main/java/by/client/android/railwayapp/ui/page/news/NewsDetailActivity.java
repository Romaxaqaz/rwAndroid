package by.client.android.railwayapp.ui.page.news;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import by.client.android.railwayapp.R;
import by.client.android.railwayapp.support.common.StartActivityBuilder;

@EActivity(R.layout.activity_news_detail)
public class NewsDetailActivity extends Activity {

    private static final String STATION = "div[class=page-content-wrapper]";

    @ViewById(R.id.newsTextView)
    TextView newsTextView;

    @Extra(NewsActivityFragment.NEWS_URL_KEY)
    String newsUrl;

    public static void start(@NotNull Activity activity, @NotNull String newsUrl, int requestCode) {
        StartActivityBuilder.create(activity, NewsDetailActivity_.class)
                .param(NewsActivityFragment.NEWS_URL_KEY, newsUrl)
                .startForResult(requestCode);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @AfterViews
    void onCreate() {
        Ion.with(getApplicationContext())
                .load(newsUrl)
                .asString()
                .withResponse()
                .setCallback((exception, result) -> {
                    Document doc = Jsoup.parse(result.getResult());
                    String content = doc.select(STATION).text();
                    newsTextView.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
                });
    }
}
