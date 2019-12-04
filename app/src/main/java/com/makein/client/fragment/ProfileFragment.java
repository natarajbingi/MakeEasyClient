package com.makein.client.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.makein.client.R;
import com.makein.client.controller.BitmapTransform;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;
import com.makein.client.models.LoginRes;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    Toolbar toolbar;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        toolbar = v.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);
        context = getContext();

        initVals(v);

        return v;
    }

    private void initVals(View v) {
        LoginRes rr = (LoginRes) Sessions.getUserObj(context, Controller.LoginRes, LoginRes.class);
        ImageView paymentlogo = v.findViewById(R.id.paymentlogo);
        EditText name_profile = v.findViewById(R.id.name_profile);
        EditText lastname_profile = v.findViewById(R.id.lastname_profile);
        EditText email_profile = v.findViewById(R.id.email_profile);
        EditText mobile_profile = v.findViewById(R.id.mobile_profile);
        EditText dob_profile = v.findViewById(R.id.dob_profile);
        EditText address1_profile = v.findViewById(R.id.address1_profile);
        EditText address2_profile = v.findViewById(R.id.address2_profile);
        EditText landmark_profile = v.findViewById(R.id.landmark_profile);
        EditText pin_profile = v.findViewById(R.id.pin_profile);
        Button btn_profile = v.findViewById(R.id.btn_profile);

        name_profile.setText(rr.data.get(0).first_name);
        lastname_profile.setText(rr.data.get(0).last_name);
        email_profile.setText(rr.data.get(0).email_id);
        mobile_profile.setText(rr.data.get(0).mobile_no);
        dob_profile.setText(rr.data.get(0).dob);
        address1_profile.setText(rr.data.get(0).address_one);
        address2_profile.setText(rr.data.get(0).address_two);
        landmark_profile.setText(rr.data.get(0).Landmark);
        pin_profile.setText(rr.data.get(0).pincode);
        RadioButton male_signup =(RadioButton) v.findViewById(R.id.male_signup);
        RadioButton female_signup =(RadioButton) v.findViewById(R.id.female_signup);
        if (rr.data.get(0).gender.equalsIgnoreCase("MALE")) {
            male_signup.setChecked(true);
        } else {
            female_signup.setChecked(true);
        }

        try {
            int size = (int) Math.ceil(Math.sqrt(800 * 600));
            // Loads given image
            Picasso.get()
                    .load(rr.data.get(0).profile_img)
                    .transform(new BitmapTransform(800, 600))
                    .resize(size, size)
                    .centerInside()
                    // .noPlaceholder()
                    .placeholder(R.drawable.loader)
                    .error(R.drawable.load_failed)
                    .into(paymentlogo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.Toasty(context, "In Progress...");
            }
        });
    }
}
