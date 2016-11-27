package com.mallock.messiiitd.fragments.postWall;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.mallock.messiiitd.DataSupplier;
import com.mallock.messiiitd.MainActivity;
import com.mallock.messiiitd.R;
import com.mallock.messiiitd.misc.OnCloseListener;
import com.mallock.messiiitd.retrofit.FileUploadService;
import com.mallock.messiiitd.retrofit.PostPost;
import com.mallock.messiiitd.retrofit.WallService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Guroosh Chaudhary on 13-11-2016.
 */

public class AddPostFragment extends DialogFragment {
    MainActivity activity;
    OnCloseListener listener;

    public void setListener(OnCloseListener listener) {
        this.listener = listener;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int SELECT_IMAGE = 2;
    static final int RESULT_OK = -1;
    private static final String TAG = AddPostFragment.class.getSimpleName();
    ImageButton attachmentButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View parent = inflater.inflate(R.layout.fragment_dialog_add, container, false);
        Button uploadPost = (Button) parent.findViewById(R.id.button_add_post);
        Button cancelButton = (Button) parent.findViewById(R.id.cancel_button);
        attachmentButton = (ImageButton) parent.findViewById(R.id.upload_photo);
        final EditText upload_text = (EditText) parent.findViewById(R.id.upload_post);
        attachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder getImageFrom = new AlertDialog.Builder(getActivity());
                final CharSequence[] opsChars = {"CAMERA", "GALLERY"};
                getImageFrom.setItems(opsChars, new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            /**camera**/
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                        } else if (which == 1) {
                            /**gallery**/
                            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(i, SELECT_IMAGE);
                        }
                        dialog.dismiss();
                    }
                });
                getImageFrom.show();

            }
        });
        uploadPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PostPost post = new PostPost();
                post.setText(upload_text.getText().toString());
                post.setUserId(DataSupplier.getUserId());
                post.setImgURL("");
                post.setUserImgURL("");
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Sending post...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                final WallService wallService = DataSupplier.getRetrofit().create(WallService.class);
                Call<Integer> call = wallService.addPost(post);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Response<Integer> response, Retrofit retrofit) {
                        progressDialog.hide();
                        try {
                            Log.e(TAG,response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (response.body() != 1) {
                            Toast.makeText(getActivity(), "got response as : " + response.body(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "post uploaded to wall", Toast.LENGTH_LONG).show();
                        }
                        if (listener != null) {
                            listener.onClose(getActivity());
                        }
                        AddPostFragment.this.dismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        progressDialog.hide();
                        Log.e(TAG,t.getMessage());
                        if (listener != null) {
                            listener.onClose(getActivity());
                        }
                        AddPostFragment.this.dismiss();
                    }
                });
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPostFragment.this.dismiss();
            }
        });


        return parent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            attachmentButton.setImageBitmap(imageBitmap);
            attachmentButton.setScaleType(ImageView.ScaleType.FIT_XY);
            file = saveBitmap(imageBitmap);
            sendImageToServer(file);
        } else if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap imageBitmap = BitmapFactory.decodeFile(filePath);
            attachmentButton.setImageBitmap(imageBitmap);
            attachmentButton.setScaleType(ImageView.ScaleType.FIT_XY);
            sendImageToServer(new File(filePath));
        }
    }

    /**
     * Sends file to server
     * @param file
     */
    private void sendImageToServer(File file) {
        // create upload service client
        FileUploadService service =
                DataSupplier.getRetrofit().create(FileUploadService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);


        // finally, execute the request
        Call<String> call = service.upload(body);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                String res = response.body();
                Toast.makeText(getActivity(), "got response" + res, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(getActivity(), "error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * saves the supplied bitmap to a file on external storage and returns the file
     * @param bmp
     * @return
     */
    private File saveBitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}
