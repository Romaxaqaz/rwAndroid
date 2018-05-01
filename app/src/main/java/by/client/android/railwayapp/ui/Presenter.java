package by.client.android.railwayapp.ui;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Presenter {

    private CompositeDisposable mCompositeDisposable;

    public void onViewAttached() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void onViewDetached() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected <T> ObservableTransformer<T, T> applyBinding() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(final Observable<T> upstream) {
                return upstream
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                bindToLifecycle(disposable);
                            }
                        });
            }
        };
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
