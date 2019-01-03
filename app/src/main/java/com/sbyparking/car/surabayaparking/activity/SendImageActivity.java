package com.sbyparking.car.surabayaparking.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sbyparking.car.surabayaparking.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendImageActivity extends AppCompatActivity {

    private static final int REQUEST_SELECT_PICT = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    private static final int ACTION_SELECT_IMAGE = 1;
    private static final int ACTION_CAPTURE_IMAGE = 2;

    @BindView(R.id.ivImagePreview) ImageView ivImagePreview;

    private Uri imagePath;
    String tmpImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_image);

        ButterKnife.bind(this);

        int action = getIntent().getIntExtra("action", 1);
        if (action == ACTION_SELECT_IMAGE) {
            selectImage();
        } else if (action == ACTION_CAPTURE_IMAGE) {
            captureImage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_PICT && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imagePath = data.getData();

            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                ivImagePreview.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_IMAGE_CAPTURE) {
            try {
                Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                ivImagePreview.setImageBitmap(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            finish();
        }
    }

    private void captureImage() {

        if (ContextCompat.checkSelfPermission(SendImageActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SendImageActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2121);

            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (imageFile != null) {
                imagePath = FileProvider.getUriForFile(this,
                        "com.sbyparking.car.surabayaparking",
                        imageFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void selectImage() {
        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");
        startActivityForResult(pickIntent, REQUEST_SELECT_PICT);
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        tmpImagePath = image.getAbsolutePath();
        return image;
    }

    @OnClick(R.id.btnSendImage)
    public void sendImage() {
        Intent data = new Intent();
        data.setData(imagePath);
        setResult(RESULT_OK, data);
        finish();
    }
}
