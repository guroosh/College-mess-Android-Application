package com.mallock.messiiitd;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.mallock.messiiitd.fragments.StatsFragment;
import com.mallock.messiiitd.fragments.menu.MenuFragment;
import com.mallock.messiiitd.fragments.postWall.WallFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBarChild();
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment newFragment;
                switch (tabId) {
                    case R.id.tab_stats:
                        newFragment = new StatsFragment();
                        break;
                    case R.id.tab_menu:
                        newFragment = new MenuFragment();
                        break;
                    case R.id.tab_wall:
                        newFragment = new WallFragment();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "UNKNOWN TAG FOR TAB", Toast.LENGTH_SHORT).show();
                        return;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contentContainer, newFragment);
//                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
