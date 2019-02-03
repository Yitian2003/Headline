package com.witlife.headline.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class LazyLoadFragment<T extends IBasePresenter> extends BaseFragment<T> {
    protected boolean isViewInitiated;

    protected boolean isVisibleToUser;

    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser){
            prepareFetchData();
        }
    }

    public void prepareFetchData(boolean forceUpdate) {
        if(isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)){
            fetchData();
            isDataInitiated = true;
        }
    }


    public void prepareFetchData() {
        prepareFetchData(false);
    }

    public abstract void fetchData();
}
