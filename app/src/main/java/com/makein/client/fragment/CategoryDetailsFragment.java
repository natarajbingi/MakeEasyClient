package com.makein.client.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.makein.client.R;
import com.makein.client.adapter.ViewPagerAdapter;
import com.makein.client.controller.BitmapTransform;
import com.makein.client.controller.Controller;
import com.makein.client.models.MyResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailsFragment extends Fragment {
    // Toolbar toolbar ;
    private Context context;

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
        if(item.sell_cost!=null || !item.sell_cost.isEmpty()){
            amt = "Rs. "+item.sell_cost+" /-";
        }
        prod_prices.setText(amt);
        prod_Desc.setText(item.description);

        btnPrev.setOnClickListener(onClickListener(0));
        btnNext.setOnClickListener(onClickListener(1));
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.Toasty(context, "In Progress...");

            }
        });

        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), img_urls);
        viewPager.setAdapter(adapter);

        inflateThumbnails();
        return v;
    }
    public class CollectingValues{
        public String prodId;
        public String subProdId;
        public String useId;
        public String userName;
        public String userContactNo;
        public String userQuery;
        public String userAddress;
        public String userCompanyName;
        public String userCompanyAddress;
        public String userEmailAddress;
        public String userComment;
    }
    private void somethingCollect (){

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

    /*private void setImagesData() {
        for (int resourceID : resourceIDs) {
            // images.add(resourceID);
        }
    }*/

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
}
