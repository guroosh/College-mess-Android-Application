package com.mallock.messiiitd;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FullMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_menu);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading image...");
        progressDialog.show();
        Picasso.with(this)
                .load(DataSupplier.getImageURL())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressDialog.hide();
                    }

                    @Override
                    public void onError() {
                        progressDialog.hide();
                        Toast.makeText(FullMenuActivity.this, "Unable to load menu", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void hideImage(View v) {
        finish();
    }
}
