package com.witlife.headline.module.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.witlife.headline.R;
import com.witlife.headline.util.SettingUtil;

import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListFragment <T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected MultiTypeAdapter adapter;
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(SettingUtil.getInstance().getColor());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

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
    public void onHideLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onShowLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void fetchData() {
        RxBus
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
