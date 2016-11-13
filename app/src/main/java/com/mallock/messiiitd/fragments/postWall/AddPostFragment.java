package com.mallock.messiiitd.fragments.postWall;

import android.app.DialogFragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mallock.messiiitd.R;

/**
 * Created by Guroosh Chaudhary on 13-11-2016.
 */

public class AddPostFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_dialog_add, container, false);
        Button button = (Button) getDialog().findViewById(R.id.button_add_post);
        ImageButton upload_photo = (ImageButton) getDialog().findViewById(R.id.upload_photo);
        EditText upload_text = (EditText) getDialog().findViewById(R.id.upload_post);
        upload_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: upload image from gallery/camera
            }
        });

        upload_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call http to add post
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
