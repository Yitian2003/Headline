
package com.witlife.headline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.witlife.headline.module.news.NewsTabLayout;
import com.witlife.headline.widget.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;
    private static final int FRAGMENT_NEWS = 0;
    private int position;
    private NewsTabLayout newsTablayout;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if (savedInstanceState != null){
            getSupportFragmentManager().findFragmentByTag(NewsTabLayout.class.getName());
        }
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
                        //doubleClick(FRAGMENT_NEWS);
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

    private void hideFragment(FragmentTransaction ft) {
        if (newsTablayout != null){
            ft.hide(newsTablayout);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
