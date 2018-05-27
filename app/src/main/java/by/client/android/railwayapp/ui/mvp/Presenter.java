package by.client.android.railwayapp.ui.mvp;

import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class Presenter {

    private CompositeDisposable mCompositeDisposable;

    protected void onViewAttached() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onViewDetached() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected <T> ObservableTransformer<T, T> applyBinding() {
        return upstream -> upstream.doOnSubscribe(this::bindToLifecycle);
    }

    /**
     * Binds a disposable to this presenter lifecycle
     *
     * @param d Disposable to be added
     */
    private void bindToLifecycle(Disposable d) {
        mCompositeDisposable.add(d);
    }
}
