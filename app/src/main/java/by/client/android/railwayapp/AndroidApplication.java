package by.client.android.railwayapp;

import android.app.Application;

/**
 * Класс инициализации приложения
 *
 * @author PRV
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
                .applicationCacheModule(new ApplicationCacheModule(this))
                .build();
        }
        return applicationComponent;
    }

    private void initCrashReporting() {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> { });
    }
}
