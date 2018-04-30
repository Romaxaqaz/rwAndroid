package by.client.android.railwayapp.ui;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
