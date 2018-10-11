
package com.witlife.headline;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.witlife.headline.module.news.NewsTabLayout;
import com.witlife.headline.widget.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView bottom_navigation;

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
        BottomNavigationViewHelper.disableShifMode(bottom_navigation);
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


    }

    private void showFragment(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
    }

    private void hideFragment(FragmentTransaction ft) {
        if (newsTabLayout != null){
            ft.hide(newsTabLayout);
        }
    }
}
