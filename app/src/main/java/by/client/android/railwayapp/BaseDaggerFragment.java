package by.client.android.railwayapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Базовый фрагмент для реализации привязки к Dagger
 *
 * @autor PRV
 */
public abstract class BaseDaggerFragment extends Fragment {

    ApplicationComponent applicationComponent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        applicationComponent = AndroidApplication.getApp().getApplicationComponent();
        BaseDaggerActivity activityDagger = (BaseDaggerActivity) getActivity();
        ApplicationComponent component = activityDagger.getComponent();
        injectFragment(component);
    }

    public abstract void injectFragment(ApplicationComponent component);
}
