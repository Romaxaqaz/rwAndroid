package by.client.android.railwayapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import by.client.android.railwayapp.AndroidApplication;
import by.client.android.railwayapp.ApplicationComponent;

/**
 * Базовая активити для реализации привязки к Dagger
 *
 * @author PRV
 */
public abstract class BaseDaggerActivity extends AppCompatActivity {

    ApplicationComponent applicationComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        applicationComponent = AndroidApplication.getApp().getApplicationComponent();
        injectActivity(applicationComponent);

    }

    ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public abstract void injectActivity(ApplicationComponent component);
}
