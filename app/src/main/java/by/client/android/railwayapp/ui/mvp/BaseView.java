package by.client.android.railwayapp.ui.mvp;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
