package com.witlife.headline.module.news.channel;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.witlife.headline.Constant;
import com.witlife.headline.R;
import com.witlife.headline.database.dao.NewsChannelDao;
import com.witlife.headline.module.base.BaseActivity;

import java.time.Instant;

public class NewsChannelActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private NewsChannelDao dao = new NewsChannelDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_channel);

        initView();
        initData();
    }

    private void initData() {
        dao.query(Constant.NEWS_CHANNEL_ENABLE);
    }

    private void initView() {
        initToolBar((Toolbar) findViewById(R.id.toolbar), true, getString(R.string.title_item_drag));
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void onStop() {
        super.onStop();
        onSaveData();
    }

    private void onSaveData() {

    }
}
