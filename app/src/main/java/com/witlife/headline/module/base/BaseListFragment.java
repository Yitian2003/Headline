package com.witlife.headline.module.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment <T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected MultiTypeAdapter adapter;
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
