package com.mallock.messiiitd.fragments.postWall;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mallock.messiiitd.R;

/**
 * Created by Guroosh Chaudhary on 13-11-2016.
 */

public class AddPostFragment extends DialogFragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_IMAGE = 2;
    static final int RESULT_OK = -1;
    ImageButton upload_photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View parent = inflater.inflate(R.layout.fragment_dialog_add, container, false);
        Button button = (Button) parent.findViewById(R.id.button_add_post);
        upload_photo = (ImageButton) parent.findViewById(R.id.upload_photo);
        EditText upload_text = (EditText) parent.findViewById(R.id.upload_post);
        upload_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: upload image from gallery/camera

//                Toast.makeText(getActivity(),"YO",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder getImageFrom = new AlertDialog.Builder(getActivity());
//                getImageFrom.setTitle("Select:");
                final CharSequence[] opsChars = {"CAMERA", "GALLERY"};
                getImageFrom.setItems(opsChars, new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                        } else if (which == 1) {
//                            Intent intent = new Intent();
//                            intent.setType("image/*");
//                            intent.setAction(Intent.ACTION_PICK);
//                            startActivityForResult(Intent.createChooser(intent,"CHOOSE"), SELECT_IMAGE);
                            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, SELECT_IMAGE);
                        }
                        dialog.dismiss();
                    }
                });
                getImageFrom.show();

            }
        });

        upload_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: call http to add post
            }
        });
        return parent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            upload_photo.setImageBitmap(imageBitmap);
            upload_photo.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap imageBitmap = BitmapFactory.decodeFile(filePath);
            upload_photo.setImageBitmap(imageBitmap);
            upload_photo.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
