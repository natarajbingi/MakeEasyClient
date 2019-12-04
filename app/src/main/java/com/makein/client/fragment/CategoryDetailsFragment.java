package com.makein.client.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.makein.client.R;
import com.makein.client.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class CategoryDetailsFragment extends Fragment {
    // Toolbar toolbar ;
    Context context;

    private ArrayList<Integer> images;
    private BitmapFactory.Options options;
    private ViewPager viewPager;
    private View btnNext, btnPrev;
    private FragmentStatePagerAdapter adapter;
    private LinearLayout thumbnailsContainer;
    private final static int[] resourceIDs = new int[]{R.drawable.apple, R.drawable.apple,
            R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple, R.drawable.apple};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_deials_fragment, container, false);

        context = getContext();

        images = new ArrayList<>();
        //find view by id
        viewPager = (ViewPager) v.findViewById(R.id.view_pager);
        thumbnailsContainer = (LinearLayout) v.findViewById(R.id.container);
        btnNext = v.findViewById(R.id.next);
        btnPrev = v.findViewById(R.id.prev);

        btnPrev.setOnClickListener(onClickListener(0));
        btnNext.setOnClickListener(onClickListener(1));

        setImagesData();

        // init viewpager adapter and attach
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), images);
        viewPager.setAdapter(adapter);

        inflateThumbnails();
        return v;
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

    private void setImagesData() {
        for (int i = 0; i < resourceIDs.length; i++) {
            images.add(resourceIDs[i]);
        }
    }

    private void inflateThumbnails() {
        for (int i = 0; i < images.size(); i++) {
            View imageLayout = getLayoutInflater().inflate(R.layout.pager_item, null);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_thumb);
            imageView.setOnClickListener(onChagePageClickListener(i));
            options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            options.inDither = false;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images.get(i), options);
            imageView.setImageBitmap(bitmap);
            //set to image view
            imageView.setImageBitmap(bitmap);
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
