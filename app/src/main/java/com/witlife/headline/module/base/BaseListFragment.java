package com.witlife.headline.module.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class BaseListFragment <T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView recyclerView;
    @Override
    public void onRefresh() {
        int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        if(firstVisibleItemPosition == 0){
            presenter.doRefresh();
            return;
        }
        recyclerView.scrollToPosition(5);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
