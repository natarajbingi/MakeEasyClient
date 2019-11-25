package com.makein.client.controller;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class Controller extends Application {

    public static final String userId = "userId";
    public static final String password = "password";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkSecurPermission(Context ctx) {

        int result1 = ContextCompat.checkSelfPermission(ctx, CAMERA);
        int result2 = ContextCompat.checkSelfPermission(ctx, READ_PHONE_STATE);
        int result3 = ContextCompat.checkSelfPermission(ctx, READ_EXTERNAL_STORAGE);
        // int result4 = ContextCompat.checkSelfPermission(ctx, WRITE_EXTERNAL_STORAGE);
        return /*result4 == PackageManager.PERMISSION_GRANTED && */result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;

    }



}
