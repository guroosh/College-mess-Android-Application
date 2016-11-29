package com.mallock.messiiitd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mallock.messiiitd.fragments.StatsFragment;
import com.mallock.messiiitd.fragments.menu.MenuFragment;
import com.mallock.messiiitd.fragments.postWall.WallFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * TODO:
 *
 * 3. GETaCTIVITY RETURNING NULL AT TIMES
 * 4. splash screen
 * 5. Polling
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mActivity = this;
        //Adding toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        setToolBarChild();

        permissionCheck();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.resource_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form:
                Intent i1 = new Intent(this, FormActivity.class);
                startActivity(i1);
                break;
            case R.id.comp:
                Intent i2 = new Intent(this, ComplaintActivity.class);
                startActivity(i2);
                break;
            case R.id.logout:
                //goto login screen
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void permissionCheck() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    finish();
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
