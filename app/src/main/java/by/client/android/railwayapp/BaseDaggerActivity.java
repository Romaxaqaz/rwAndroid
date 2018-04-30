package by.client.android.railwayapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Базовая активити для реализации привязки к Dagger
 *
 * @author PRV
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {

    ApplicationComponent applicationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applicationComponent = AndroidApplication.getApp().getApplicationComponent();
        injectActivity(applicationComponent);
        super.onCreate(savedInstanceState);
    }

    ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public abstract void injectActivity(ApplicationComponent component);
}
