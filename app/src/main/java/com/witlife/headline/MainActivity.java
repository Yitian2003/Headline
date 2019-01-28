
package com.witlife.headline;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.witlife.headline.module.news.NewsTabLayout;
import com.witlife.headline.util.SettingUtil;
import com.witlife.headline.widget.helper.BottomNavigationViewHelper;

import java.util.Set;

import static com.witlife.headline.database.table.NewsChannelTable.POSITION;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;
    private static final int FRAGMENT_NEWS = 0;
    private int position;
    private NewsTabLayout newsTablayout;
    //private PhotoTabLayou photoTabLayou;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private long exitTime = 0;
    private long firstClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (savedInstanceState != null){
            newsTablayout = (NewsTabLayout) getSupportFragmentManager().findFragmentByTag(NewsTabLayout.class.getName());
            showFragment(savedInstanceState.getInt(POSITION));
            bottom_navigation.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_NEWS);
        }

        if(SettingUtil.getInstance().getIsFirstTime()){
            showTapTarget();
        }
    }

    private void showTapTarget() {
        Display display = getWindowManager().getDefaultDisplay();
        Rect target = new Rect(0, display.getHeight(), 0, display.getHeight());

        target.offset(display.getWidth() / 8, -56);

        TapTargetSequence sequence = new TapTargetSequence(this)
                .targets(
                TapTarget.forToolbarMenuItem(toolbar, R.id.action_search, "Click here to search")
                        .dimColor(android.R.color.black)
                        .outerCircleColor(R.color.colorPrimary)
                        .drawShadow(true)
                        .id(1),
                TapTarget.forToolbarNavigationIcon(toolbar, "Click here to expand drawer")
                        .dimColor(android.R.color.black)
                        .outerCircleColor(R.color.colorPrimary)
                        .drawShadow(true)
                        .id(2),
                TapTarget.forBounds(target, "Click here to switch News", "Double click back to the top\nDouble click refresh current page")
                        .dimColor(android.R.color.black)
                        .outerCircleColor(R.color.colorPrimary)
                        .targetRadius(60)
                        .transparentTarget(true)
                        .drawShadow(true)
                        .id(3)
                ).listener(new TapTargetSequence.Listener() {
                    @Override
                    public void onSequenceFinish() {
                        SettingUtil.getInstance().setIsFirstTime(false);
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {

                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        SettingUtil.getInstance().setIsFirstTime(false);

                    }
                });
        sequence.start();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottom_navigation);
        setSupportActionBar(toolbar);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_news:
                        showFragment(FRAGMENT_NEWS);
                        doubleClick(FRAGMENT_NEWS);
                        break;
                }
                return true;
            }
        });

        drawer_layout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
    }

    public void doubleClick(int index) {
        long secondClickTime = System.currentTimeMillis();
        if((secondClickTime - firstClickTime < 500)){
            switch (index){
                case FRAGMENT_NEWS:
                    newsTablayout.onDoubleClick();
                    break;
            }
        } else {
            firstClickTime = secondClickTime;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // record current position when recreate
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, bottom_navigation.getSelectedItemId());
    }

    private void showFragment(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        switch (index){
            case FRAGMENT_NEWS:
                toolbar.setTitle("Headline");
                if(newsTablayout == null){
                    newsTablayout = NewsTabLayout.getInstance();
                    ft.add(R.id.container, newsTablayout, NewsTabLayout.class.getName());
                } else {
                    ft.show(newsTablayout);
                }
                break;
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if((currentTime - exitTime) < 2000){
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.double_click_exit, Toast.LENGTH_LONG);
            exitTime = currentTime;
        }
    }

    private void hideFragment(FragmentTransaction ft) {
        if (newsTablayout != null){
            ft.hide(newsTablayout);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_switch_night_mode:
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if(mode == Configuration.UI_MODE_NIGHT_YES){
                    SettingUtil.getInstance().setIsNightMode(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    SettingUtil.getInstance().setIsNightMode(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                recreate();
                return false;
        }
        return false;
    }
}
