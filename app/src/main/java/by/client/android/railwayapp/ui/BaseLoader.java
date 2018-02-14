package by.client.android.railwayapp.ui;

import by.client.android.railwayapp.support.RegisterLoader;
import by.client.android.railwayapp.support.SafeRegistrationSendListener;

/**
 * Базовая реализация загрузчика
 *
 * @author PRV
 */
public abstract class BaseLoader<Param, Result> extends BaseAsyncTask<Param, Result> {

    private final RegisterLoader registerLoader;

    public BaseLoader(RegisterLoader registerLoader) {
        this.registerLoader = new SafeRegistrationSendListener(registerLoader);
    }

    @Override
    protected void onCompleted(Result param) {
        registerLoader.onSuccess(param);
    }

    @Override
    protected void onStart() {
        registerLoader.onStart();
    }

    @Override
    protected void onFinish(boolean successful) {
        registerLoader.onFinish(successful);
    }

    @Override
    protected Result runTask(Param... param) throws Exception {
        return call(param[0]);
    }

    @Override
    protected void onError(Exception exception) {
        registerLoader.onError(exception);
    }

    protected abstract Result call(Param param) throws Exception;
}
