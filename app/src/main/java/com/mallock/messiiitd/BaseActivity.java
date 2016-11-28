package com.mallock.messiiitd;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by Akshat on 09-06-2016.
 */
public class BaseActivity extends AppCompatActivity {
    AppCompatActivity mActivity;
    public ProgressDialog progressDialog;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    public void setToolBarChild() {
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(getResources().getColor(
                        R.color.actionbar_color)));
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);
        getSupportActionBar().setIcon(R.drawable.ic_app);
    }


    protected void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    protected void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
