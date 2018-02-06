package by.client.android.railwayapp;

import android.app.Application;

/**
 * Created by PanteleevRV on 15.01.2018.
 *
 * @author ROMAN PANTELEEV
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private static AndroidApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        getApplicationComponent().inject(this);
    }

    public static AndroidApplication getApp() {
        return application;
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        }
        return applicationComponent;
    }
}
