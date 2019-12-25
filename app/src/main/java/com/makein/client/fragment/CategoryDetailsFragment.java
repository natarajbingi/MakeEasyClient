package com.makein.client.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.makein.client.R;
import com.makein.client.ServerHit.Api;
import com.makein.client.ServerHit.RetroCall;
import com.makein.client.activities.LoginActivity;
import com.makein.client.adapter.ViewPagerAdapter;
import com.makein.client.controller.BitmapTransform;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;
import com.makein.client.models.LoginRes;
import com.makein.client.models.MyResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class CategoryDetailsFragment extends Fragment {
    // Toolbar toolbar ;
    private Context context;
    private ProgressDialog dialog;

    //    private ArrayList<Integer> images;
    private List<String> img_urls;
    //    private BitmapFactory.Options options;
    private ViewPager viewPager;
    private View btnNext, btnPrev;
    private FragmentStatePagerAdapter adapter;
    private LinearLayout thumbnailsContainer;
    private MyResponse.SubProds item;
    private String CategoryName, SubCategoryName;
    private Button add_to_cart;
    private TextView title, sub_title, prod_prices, prod_Desc;

    CategoryDetailsFragment(MyResponse.SubProds item, String CategoryName, String SubCategoryName) {
        this.item = item;
        this.CategoryName = CategoryName;
        this.SubCategoryName = SubCategoryName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_deials_fragment, container, false);
        context = getContext();
        dialog = new ProgressDialog(context);
        dialog.setMessage("in Progress,  please wait.");

        getActivity().setTitle(SubCategoryName);

        //        images = new ArrayList<>();
        img_urls = item.img_urls;
        //find view by id
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        thumbnailsContainer = (LinearLayout) v.findViewById(R.id.container);
        btnNext = v.findViewById(R.id.next);
        btnPrev = v.findViewById(R.id.prev);
        add_to_cart = v.findViewById(R.id.add_to_cart);
        title = v.findViewById(R.id.title);
        sub_title = v.findViewById(R.id.sub_title);
        prod_prices = v.findViewById(R.id.prod_prices);
        prod_Desc = v.findViewById(R.id.prod_Desc);

        title.setText(CategoryName.toUpperCase());
        sub_title.setText(SubCategoryName.toUpperCase());
        String amt = "";
        if (item.sell_cost != null || !item.sell_cost.isEmpty()) {
            amt = "Rs. " + item.sell_cost + " /-";
        }
        prod_prices.setText(amt);
        prod_Desc.setText(item.description);

        btnPrev.setOnClickListener(onClickListener(0));
        btnNext.setOnClickListener(onClickListener(1));
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow();
            }
        });

        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), img_urls);
        viewPager.setAdapter(adapter);

        inflateThumbnails();
        return v;
    }

    private void somethingCollect(String userQueryStr, String userCompanyNameStr, String userCompanyAddressStr,
                                  String userCompnyEmailAddressStr, String userContactNoStr) {
        String user_id = Sessions.getUserObject(context, Controller.userID);
        String prod_id = this.item.prod_id;
        String prod_subid = this.item.id;
        String quantity = "";
        String sell_cost = "";
        String delivery_address = Sessions.getUserObject(context, Controller.Address);
        String comment = "";

        userprodreq(user_id, prod_id, prod_subid, quantity, sell_cost
                , delivery_address, userContactNoStr, userQueryStr, userCompanyNameStr
                , userCompanyAddressStr, userCompnyEmailAddressStr, comment);
    }

    private View.OnClickListener onClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    //next page
                    if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                } else {
                    //previous page
                    if (viewPager.getCurrentItem() > 0) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                }
            }
        };
    }


    private void inflateThumbnails() {
        for (int i = 0; i < img_urls.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.pager_item, null);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_thumb);
            imageView.setOnClickListener(onChagePageClickListener(i));
            /*options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            options.inDither = false;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), img_urls.get(i), options);
            imageView.setImageBitmap(bitmap);
            //set to image view
            imageView.setImageBitmap(bitmap);*/
            try {
                int size = (int) Math.ceil(Math.sqrt(800 * 600));
                // Loads given image
                Picasso.get()
                        .load(img_urls.get(i))
                        .transform(new BitmapTransform(800, 600))
                        .resize(size, size)
                        .centerInside()
                        // .noPlaceholder()
                        .placeholder(R.drawable.loader)
                        .error(R.drawable.load_failed)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //add imageview

            thumbnailsContainer.addView(imageLayout);
        }
    }

    private View.OnClickListener onChagePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
            }
        };
    }


    private void userprodreq(String user_id, String prod_id, String prod_subid, String quantity, String sell_cost
            , String delivery_address, String userContactNo, String userQuery, String userCompanyName
            , String userCompanyAddress, String userCompnyEmailAddress, String comment) {
        dialog.show();

        //creating request body for file
        RequestBody user_idBody = RequestBody.create(MediaType.parse("text/plain"), user_id);
        RequestBody prod_idBody = RequestBody.create(MediaType.parse("text/plain"), prod_id);
        RequestBody prod_subidBody = RequestBody.create(MediaType.parse("text/plain"), prod_subid);
        RequestBody quantityBody = RequestBody.create(MediaType.parse("text/plain"), quantity);
        RequestBody sell_costBody = RequestBody.create(MediaType.parse("text/plain"), sell_cost);
        RequestBody delivery_addressBody = RequestBody.create(MediaType.parse("text/plain"), delivery_address);

        RequestBody userContactNoBody = RequestBody.create(MediaType.parse("text/plain"), userContactNo);
        RequestBody userQueryBody = RequestBody.create(MediaType.parse("text/plain"), userQuery);
        RequestBody userCompanyNameBody = RequestBody.create(MediaType.parse("text/plain"), userCompanyName);
        RequestBody userCompanyAddressBody = RequestBody.create(MediaType.parse("text/plain"), userCompanyAddress);
        RequestBody userCompnyEmailAddressBody = RequestBody.create(MediaType.parse("text/plain"), userCompnyEmailAddress);
        RequestBody commentBody = RequestBody.create(MediaType.parse("text/plain"), comment);

        //creating our api
        Api api = RetroCall.getClient();
        //creating a call and calling the upload image method
        Call<MyResponse> call = api.userprodreq(user_idBody, prod_idBody, prod_subidBody, quantityBody, sell_costBody
                , delivery_addressBody, userContactNoBody, userQueryBody, userCompanyNameBody, userCompanyAddressBody
                , userCompnyEmailAddressBody, commentBody);


        //finally performing the call
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Controller.logPrint(call.request().toString(), null, response.body());
                assert response.body() != null;
                if (!response.body().error) {

                } else {
                    Controller.Toasty(context, "Something went wrong server side...");
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Controller.Toasty(context, "Something went wrong , Please check network connection.");
                Log.d("Error", t.getMessage());
            }
        });
    }


    private void popupWindow() {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.add_sub_pop, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupView.setFocusable(true);
        AppCompatButton buttonCancel, buttonOkay;
        final EditText userQuery, userCompanyName, userCompanyAddress, userCompnyEmailAddress, userContactNo;

        userQuery = (EditText) popupView.findViewById(R.id.userQuery);
        userCompanyName = (EditText) popupView.findViewById(R.id.userCompanyName);
        userCompanyAddress = (EditText) popupView.findViewById(R.id.userCompanyAddress);
        userCompnyEmailAddress = (EditText) popupView.findViewById(R.id.userCompnyEmailAddress);
        userContactNo = (EditText) popupView.findViewById(R.id.userContactNo);

        buttonCancel = (AppCompatButton) popupView.findViewById(R.id.buttonCancel);
        buttonOkay = (AppCompatButton) popupView.findViewById(R.id.buttonOkay);


        buttonOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQueryStr = userQuery.getText().toString().trim();
                String userCompanyNameStr = userCompanyName.getText().toString().trim();
                String userCompanyAddressStr = userCompanyAddress.getText().toString().trim();
                String userCompnyEmailAddressStr = userCompnyEmailAddress.getText().toString().trim();
                String userContactNoStr = userContactNo.getText().toString().trim();
                if (userQueryStr.isEmpty() || userCompanyNameStr.isEmpty() || userContactNoStr.isEmpty()) {
                    Controller.Toasty(context, "please fill the Mandatory fields.");
                } else {
                    somethingCollect(userQueryStr, userCompanyNameStr, userCompanyAddressStr, userCompnyEmailAddressStr, userContactNoStr);
                    popupWindow.dismiss();
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(viewPager, Gravity.CENTER, 0, 0);
        //        popupWindow.showAsDropDown(toolbar, 0, 0);
    }
}
