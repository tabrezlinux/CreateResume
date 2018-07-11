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
import android.os.Environment;
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

    private Button bt_upload_picture, bt_upload_sign;
    private ImageView iv_picture, iv_sign;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private final int IMAGE_REQUEST_CODE_GALLERY = 1, IMAGE_REQUEST_CODE_CAMERA = 2, SIGN_REQUEST_CODE_GALLERY = 3, SIGN_REQUEST_CODE_CAMERA = 4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.photo_sign, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_upload_picture = (Button) view.findViewById(R.id.bt_upload_picture);
        iv_picture = (ImageView) view.findViewById(R.id.iv_picture);
        bt_upload_sign = (Button) view.findViewById(R.id.bt_upload_sign);
        iv_sign = (ImageView) view.findViewById(R.id.iv_sign);

        bt_upload_picture.setOnClickListener(this);
        iv_picture.setOnClickListener(this);

        bt_upload_sign.setOnClickListener(this);
        iv_sign.setOnClickListener(this);

    }

    private void showUploadSignDialog(){
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
                                chooseSignFromGallary();
                                break;
                            case 1:
                                takeSignFromCamera();
                                break;
                        }
                    }
                });

        pictureDialog.show();

    }

    public void chooseSignFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, SIGN_REQUEST_CODE_GALLERY);
    }

    private void takeSignFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        // check permission
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // ask for permission
            askCameraPermission(intent);

        } else {
            startActivityForResult(intent, SIGN_REQUEST_CODE_CAMERA);
        }
    }


    public String saveSignImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, "Resume_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }


    private void showUploadPictureDialog() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getContext());
//        pictureDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        pictureDialog.setPositiveButton("Pick", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
//            }
//        });


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
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });

        pictureDialog.show();

    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE_GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        // check permission
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // ask for permission
            askCameraPermission(intent);

        } else {
            startActivityForResult(intent, IMAGE_REQUEST_CODE_CAMERA);
        }
    }

    private void askCameraPermission(Intent intent) {
        // should ask for permisssion?
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {

            //ask for CAMERA permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    IMAGE_REQUEST_CODE_CAMERA);


        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    IMAGE_REQUEST_CODE_CAMERA);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE_GALLERY:
                    if (data != null) {
                        Uri contentURI = data.getData();
//                        try {


                        Picasso.with(getContext())
                                .load(contentURI)
                                .transform(new CropSquareTransformation())
                                .into(iv_picture);

//                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
////                            String path = saveImage(bitmap);
////                            Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
//                            iv_picture.setImageBitmap(bitmap);

//                        } catch (IOException e) {
//                            e.printStackTrace();
////                            Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    break;
                case IMAGE_REQUEST_CODE_CAMERA:
                    Uri cameraUri = data.getData();
                    Picasso.with(getContext())
                            .load(cameraUri)
                            .into(iv_picture);
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    iv_picture.setImageBitmap(thumbnail);
                    saveImage(thumbnail);
//                    Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    break;

                case SIGN_REQUEST_CODE_GALLERY:
                    if (data != null) {
                        Uri contentURI = data.getData();
//                        try {


                        Picasso.with(getContext())
                                .load(contentURI)
                                .transform(new CropSquareTransformation())
                                .into(iv_sign);
                    }
                    break;
                case SIGN_REQUEST_CODE_CAMERA:
                    Uri signCameraUri = data.getData();
                    Picasso.with(getContext())
                            .load(signCameraUri)
                            .into(iv_sign);
                    Bitmap signThumbnail = (Bitmap) data.getExtras().get("data");
                    iv_sign.setImageBitmap(signThumbnail);
                    saveImage(signThumbnail);
//                    Toast.makeText(getContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, "Resume_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {

            Log.d("bitmap size original", String.valueOf(source.getHeight()) + " " +String.valueOf(source.getWidth()));

            int size = Math.min(source.getWidth(), source.getHeight());

            Log.d("bitmap size min", String.valueOf(size));

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Log.d("bitmap size after", String.valueOf(x +" " + y));

            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_upload_picture:
                showUploadPictureDialog();
                Toast.makeText(getContext(), "Picture clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_picture:
                Toast.makeText(getContext(), "Picture clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_upload_sign:
                showUploadSignDialog();
                Toast.makeText(getContext(), "Picture clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_sign:
                Toast.makeText(getContext(), "Picture clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
