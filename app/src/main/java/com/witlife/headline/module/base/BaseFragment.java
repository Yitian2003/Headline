package com.witlife.headline.module.base;


import android.content.Context;
import android.support.v4.app.Fragment;

import io.reactivex.annotations.NonNull;

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T>{

    protected T presenter;
    @NonNull
    protected Context mContext;

    /**
     * @return layout file ID
     */
    protected abstract int attachLayoutId();
}
