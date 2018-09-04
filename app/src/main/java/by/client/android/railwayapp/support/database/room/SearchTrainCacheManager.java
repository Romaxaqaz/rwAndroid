package by.client.android.railwayapp.support.database.room;

import java.util.List;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import by.client.android.railwayapp.model.SearchTrain;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by PanteleevRV on 04.09.2018.
 *
 * @author Q-RPA
 */
public class SearchTrainCacheManager {

    private static final String DATABASE_NAME = "train_database";

    private SearchTrainDataBase db;

    public SearchTrainCacheManager(Context context) {
        db = Room.databaseBuilder(context, SearchTrainDataBase.class, DATABASE_NAME).build();
    }

    @SuppressLint("CheckResult")
    public void insert(SearchTrain item) {
        Completable.fromAction(() -> db.searchTrainDao().insert(item)).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void getAll(final Callback callback) {
        db.searchTrainDao()
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback::onTrainLoaded);
    }

    public interface Callback {

        void onTrainLoaded(List<SearchTrain> trains);
    }
}
