package by.client.android.railwayapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import by.client.android.railwayapp.ShellActivity_;
import by.client.android.railwayapp.support.base.BaseAsyncTask;
import by.client.android.railwayapp.ui.utils.Utils;

/**
 * Страница отображения SplashScreen
 *
 * @author Q-RPA
 */
public class SplashActivity extends Activity {

    private static final int SLEEP_TIME = 1000;

    private boolean isActiveView;
    private boolean isLoadedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoadFakeDataAsync().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActiveView = true;

        if (isLoadedData) {
            toMainPage();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActiveView = false;
    }

    private void toMainPage() {
        Intent intent = new Intent(SplashActivity.this, ShellActivity_.class);
        startActivity(intent);
        finish();
    }

    private final class LoadFakeDataAsync extends BaseAsyncTask<Void, Void> {

        @Override
        protected Void runTask(Void... voids) {
            Utils.delay(SLEEP_TIME);
            return null;
        }

        @Override
        protected void onError(Exception exception) {

        }

        @Override
        protected void onCompleted(Void result) {
            if (isActiveView) {
                toMainPage();
            }
            isLoadedData = true;
        }

        @Override
        protected void onStart() {

        }

        @Override
        protected void onFinish(boolean successful) {

        }
    }
}
