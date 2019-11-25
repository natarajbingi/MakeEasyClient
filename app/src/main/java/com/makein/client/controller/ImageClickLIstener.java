package com.makein.client.controller;

import android.content.Context;
import android.net.Uri;
import android.view.View;

public class ImageClickLIstener implements View.OnClickListener {
    Uri mArrayUri;
    Context context;

    public ImageClickLIstener(Context context, Uri mArrayUri) {
//        this.position = position;
        this.mArrayUri = mArrayUri;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if (mArrayUri != null) {
            Controller.popUpImg(context, mArrayUri, "Selected Image", null,null, "URI");
        }
    }
}