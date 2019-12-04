package com.makein.client.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.makein.client.fragment.HomeFragment;
import com.makein.client.fragment.ProfileFragment;
import com.makein.client.fragment.SetttingFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                ProfileFragment tab2 = new ProfileFragment();
               return tab2;
            case 2:
                SetttingFragment tab3 = new SetttingFragment();
                return tab3;
            case 3:
                SetttingFragment tab4 = new SetttingFragment();
                return tab4;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
