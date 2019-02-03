package com.witlife.headline.module.base;

import java.util.List;

public interface IBaseListView<T> extends IBaseView<T>{

    void onShowLoading();

    void onShowNetError();

    void onHideLoading();

    void setPresenter(T presenter);

    void onSetAdapter(List<?> list);

    void onShowNoMore();

}
