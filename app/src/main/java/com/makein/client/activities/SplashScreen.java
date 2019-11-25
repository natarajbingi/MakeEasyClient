package com.makein.client.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.makein.client.R;
import com.makein.client.controller.Controller;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Controller.checkSecurPermission(getApplicationContext())) {
                Log.d("TAG-Permission: ", "Permission Granted already");
                enter();
            } else {
                requestPermission();
            }
        } else {
            enter();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA, READ_PHONE_STATE
                    , READ_EXTERNAL_STORAGE/*, WRITE_EXTERNAL_STORAGE*/}, PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean PHONEAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean STORAGEAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    // boolean WSTORAGEAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    Log.d("123", cameraAccepted + "," + PHONEAccepted + "," + STORAGEAccepted);
                    if (cameraAccepted &&/* WSTORAGEAccepted &&*/ PHONEAccepted && STORAGEAccepted) {
                        Log.d("SplashTAG ", "Permission Granted, Now you can access location data,PHONE,STORAGE and camera.");
                        enter();
                    } else {
                        Log.d("SplashTAG ", "Permission Denied, You cannot access location data and camera.");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(READ_PHONE_STATE)) {
                                showMessageOKCancel(getString(R.string.permision_text),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{/*ACCESS_FINE_LOCATION
                                                                    ,*/CAMERA, READ_PHONE_STATE, READ_EXTERNAL_STORAGE
                                                                    /*, WRITE_EXTERNAL_STORAGE*/},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }

                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashScreen.this)
                .setTitle(getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void enter() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
