package com.witlife.headline.module.base;

import com.uber.autodispose.AutoDisposeConverter;

public interface IBaseView<T> {
    void onShowLoading();

    void onHideLoading();

    void  onShowNewError();

    void setPresenter(T presenter);

    <x> AutoDisposeConverter<x> bindAutoDispose();
}
