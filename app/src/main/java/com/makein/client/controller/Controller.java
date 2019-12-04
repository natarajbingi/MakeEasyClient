package com.makein.client.controller;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;


import com.google.gson.Gson;
import com.makein.client.R;
import com.makein.client.models.MyResponse;
import com.squareup.picasso.Picasso;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;

public class Controller extends Application {

    public static final String userId = "userId";
    public static final String password = "password";
    public static final String userID = "userID";
    public static final String emailID = "emailID";
    public static final String name = "name";
    public static final String Address = "Address";
    public static final String profile_img = "profile_img";
    public static final String LoginRes = "LoginRes";
    public static final String keepMeSignedStr = "keepMeSignedStr";
    public static final String Categories = "Categories";
    public static final String appVersion = "1.0.1";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkSecurPermission(Context ctx) {

        int result1 = ContextCompat.checkSelfPermission(ctx, CAMERA);
        int result2 = ContextCompat.checkSelfPermission(ctx, READ_PHONE_STATE);
        int result3 = ContextCompat.checkSelfPermission(ctx, READ_EXTERNAL_STORAGE);
        // int result4 = ContextCompat.checkSelfPermission(ctx, WRITE_EXTERNAL_STORAGE);
        return /*result4 == PackageManager.PERMISSION_GRANTED && */result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;

    }


    public static void Toasty(Context c, String Msg) {
        Toast.makeText(c, Msg, Toast.LENGTH_LONG).show();
    }

    public static boolean isOnline(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            //Toast.makeText(context, activeNetworkInfo.getTypeName(), Toast.LENGTH_SHORT).show();

            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public static void logPrint(String call, Object req, Object res) {
        Gson g = new Gson();
        if (call != null)
            Log.d("Request-", call);
        if (req != null)
            Log.d("LogReq-", g.toJson(req));
        if (res != null)
            Log.d("LogRes-", g.toJson(res));
    }

    public static Map<String, String> convertMapArr(List<MyResponse.Data> data) {
        Map<String, String> ReturnArray = new LinkedHashMap<String, String>();
        ReturnArray.put("Select", "Select");
        for (int i = 0; i < data.size(); i++) {
            ReturnArray.put(data.get(i).name.toUpperCase() + "-" + data.get(i).id, data.get(i).id);
        }

        return ReturnArray;
    }

    public static Dialog popUpImg(Context context, Uri uri, String ImgCredit, String encodedImgORUrl, Bitmap bitmap, String Type) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.photo_popup);
        RelativeLayout photoId = (RelativeLayout) dialog.findViewById(R.id.photoId);

        //        RelativeLayout signId = (RelativeLayout) dialog.findViewById(R.id.signId);
        //        signId.setVisibility(View.GONE);
        photoId.setVisibility(View.VISIBLE);
        ImageView closeImgPopUp = (ImageView) dialog.findViewById(R.id.closeImgPopUp);
        ImageView imgPopup = (ImageView) dialog.findViewById(R.id.imgPopup);
        TextView headingText = (TextView) dialog.findViewById(R.id.headingText);
        headingText.setText(ImgCredit);

        closeImgPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        if (Type.equals("URI")) {
            imgPopup.setImageURI(uri);
        } else if (Type.equals("NOT_URL")) {
            byte[] bytes = Base64.decode(encodedImgORUrl, Base64.DEFAULT);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imgPopup.setImageBitmap(bitmap1);
        } else if (Type.equals("bitMap")) {
            imgPopup.setImageBitmap(bitmap);
        } else {//if (!Type.equals("URL")) {
            try {
                // Loads given image
                //  int size = (int) Math.ceil(Math.sqrt(800 * 600));
                Picasso.get()
                        .load(encodedImgORUrl)
                        // .transform(new BitmapTransform(800, 600))
                        // .resize(size, size)
                        // .centerInside()
                        // .noPlaceholder()
                        .placeholder(R.drawable.loader)
                        .error(R.drawable.load_failed)
                        .into(imgPopup);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dialog.show();
        return dialog;
    }


}
