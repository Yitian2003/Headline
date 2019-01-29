package com.witlife.headline.module.base;


import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.annotations.NonNull;

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView<T>{

    protected T presenter;
    @NonNull
    protected Context mContext;

    /**
     * @return layout file ID
     */
    protected abstract int attachLayoutId();

    protected abstract void initView(View view);

    protected abstract void initData() throws NullPointerException;

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title){
        ((BaseActivity)getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(@android.support.annotation.NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
        .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
