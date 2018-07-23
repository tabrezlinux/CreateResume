package com.example.tabrezahmad.createresume;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class PhotoAndSignFrag extends Fragment implements View.OnClickListener {

    private Button bt_photo_pick, bt_sign_pick;
    private ImageView iv_photo, iv_sign;
    private final int
            REQUEST_CODE_PHOTO_FROM_GALLERY = 1,
            REQUEST_CODE_PHOTO_FROM_CAMERA = 2,
            REQUEST_CODE_SIGN_FROM_GALLERY = 3,
            REQUEST_CODE_SIGN_FROM_CAMERA = 4;

    private static final String IMAGE_DIRECTORY = "/demonuts";  // FOLDER NAME
    private static String PREFIX_PHOTO = "PHOTO";               // e.g. PHOTO_xxx.jpg
    private static String PREFIX_SIGN = "SIGN";                 // e.g. SIGN_xxx.jpg
    File camera_photo_file_path = null;
    File camera_sign_file_path = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.photo_sign, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt_photo_pick = view.findViewById(R.id.bt_upload_picture);
        iv_photo = view.findViewById(R.id.iv_picture);
        bt_sign_pick = view.findViewById(R.id.bt_upload_sign);
        iv_sign = view.findViewById(R.id.iv_sign);

        bt_photo_pick.setOnClickListener(this);
        bt_sign_pick.setOnClickListener(this);

    }


    // PHOTO DIALOG, CHOOSE AND SAVE
    private void pickSignDialog() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());

        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select sign from gallery",
                "Capture sign from camera"};

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseSignFrom(0);         // 0 = gallery
                                break;
                            case 1:
                                chooseSignFrom(1);          // 1 = camera
                                break;
                        }
                    }
                });

        pictureDialog.show();

    }

    // choose
    public void chooseSignFrom(int From) {

        Intent intent = null;

        switch (From) {

            // 0 = from gallery
            case 0:
                intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_SIGN_FROM_GALLERY);
                break;

            // 1 = from camera
            case 1:

                // create photo destination file path
                camera_sign_file_path = createFileOnExtStorage(PREFIX_SIGN);

                // create intent with extra, destination path data
                intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);                  // action for camera

                // put destination path data
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(camera_sign_file_path));   // where to save

                // if application package has no camera permission
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // then ask permission
                    askCameraPermission(intent);
                    // else use camera
                } else {
                    startActivityForResult(intent, REQUEST_CODE_SIGN_FROM_CAMERA);
                }

                break;
        }
    }



    // PHOTO DIALOG, CHOOSE AND SAVE
    private void pickPhotoDialog() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());

        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFrom(0);         // 0 = gallery
                                break;
                            case 1:
                                choosePhotoFrom(1);      // 1 = camera
                                break;
                        }
                    }
                });

        pictureDialog.show();

    }

    // choose
    public void choosePhotoFrom(int From) {

        Intent intent = null;

        switch (From) {

            // 0 = from gallery
            case 0:
                intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_PHOTO_FROM_GALLERY);
                break;

            // 1 = from camera
            case 1:

                // create photo destination file path
                camera_photo_file_path = createFileOnExtStorage(PREFIX_PHOTO);

                // create intent with extra, destination path data
                intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);                  // action for camera

                // put destination path data
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(camera_photo_file_path));   // where to save

                // if application package has no camera permission
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // then ask permission
                    askCameraPermission(intent);
                    // else use camera
                } else {
                    startActivityForResult(intent, REQUEST_CODE_PHOTO_FROM_CAMERA);
                }

                break;
        }
    }


    // create file for SIGN and PHOTO --------------------------------------------------------------
    @NonNull
    private File createFileOnExtStorage(String file_prefix) {

        // 1. build directory, if needed. -- optional
        File directory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
            Log.e("Directory", "directory created : " + directory.getAbsolutePath());
        } else {
            Log.e("Directory", "Error creating directory, already exists : " + directory.getAbsolutePath());
        }

        // 2. create file
        File f = null;
        try {
            f = new File(directory, file_prefix + "_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            Log.e("File", "File is created : " + f.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
            Log.e("File", "Error creating file : " + f.getAbsolutePath());
        }

        return f;
    }


    // ASK FOR CAMERA PERMISSION -------------------------------------------------------------------
    private void askCameraPermission(Intent intent) {

        // should ask for permisssion?
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {

            //ask for CAMERA permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_PHOTO_FROM_CAMERA);


        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE_PHOTO_FROM_CAMERA);
        }

    }


    // ON ACTIVITY RESULT --------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // IF REQUEST_CODE IS
        switch (requestCode) {

            case REQUEST_CODE_PHOTO_FROM_GALLERY:
                // if RESULT_OK
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        Picasso.with(getContext())
                                .load(data.getData())
                                .transform(new CropSquareTransformation())
                                .into(iv_photo);
                    }
                }
                break;

            case REQUEST_CODE_SIGN_FROM_GALLERY:
                // if RESULT_OK
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        Picasso.with(getContext())
                                .load(contentURI)
                                .transform(new CropSquareTransformation())
                                .into(iv_sign);
                    }
                }
                break;

            case REQUEST_CODE_PHOTO_FROM_CAMERA:
                // camera don't return data if EXTRA_OUTPUT intent is set
                // load the thumbnail if RESULT_OK
                if (resultCode == Activity.RESULT_OK) {
                    Picasso.with(getContext())
                            .load(camera_photo_file_path)
                            .transform(new CropSquareTransformation())
                            .into(iv_photo);
                } else {
                    boolean deleted = camera_photo_file_path.delete();
                    Toast.makeText(getContext(), "Failed to save photo", Toast.LENGTH_SHORT).show();
                    Log.e("FILE_PHOTO", String.valueOf(deleted) + " was unable to save");
                }
                break;

            case REQUEST_CODE_SIGN_FROM_CAMERA:
                // camera don't return data if EXTRA_OUTPUT intent is set
                // load the thumbnail if RESULT_OK
                if (resultCode == Activity.RESULT_OK) {
                    Picasso.with(getContext())
                            .load(camera_sign_file_path)
                            .transform(new CropSquareTransformation())
                            .into(iv_sign);
                }else {
                    boolean deleted = camera_sign_file_path.delete();
                    Toast.makeText(getContext(), "Failed to save sign", Toast.LENGTH_SHORT).show();
                    Log.e("FILE_SIGN", String.valueOf(deleted) + " was unable to save");
                }
                break;
        }

    }


    // picasso transform
    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {

            Log.d("bitmap size original", String.valueOf(source.getHeight()) + " " + String.valueOf(source.getWidth()));

            int size = Math.min(source.getWidth(), source.getHeight());

            Log.d("bitmap size min", String.valueOf(size));

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Log.d("bitmap size after", String.valueOf(x + " " + y));

            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }


    // ON CLICK
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_upload_picture:
                pickPhotoDialog();
                break;
            case R.id.bt_upload_sign:
                pickSignDialog();
                break;
        }
    }
}
