package com.makein.client.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.makein.client.fragment.PagerFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Integer> images;

    public ViewPagerAdapter(FragmentManager fm, List<Integer> imagesList) {
        super(fm);
        this.images = imagesList;
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.getInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
