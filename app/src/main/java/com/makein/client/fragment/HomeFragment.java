package com.makein.client.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.adapter.HomeAdapter;
import com.makein.client.models.HomeItems;

import java.util.ArrayList;

public class HomeFragment extends Fragment  implements  HomeAdapter.RecyclerViewClickListener{
    Toolbar toolbar ;
    Context context;
    LinearLayout home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        toolbar = v.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);
        context = getContext();
        home = v.findViewById(R.id.home_layout_);
        initViews(v);
        return v ;
    }

    private void initViews(View v){
        RecyclerView recyclerView = v.findViewById(R.id.home_recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<HomeItems> items = new ArrayList<>();
        HomeItems homeItems = new HomeItems();
        for(int i=0;i<=10;i++) {
            homeItems.setItemName("Category");

            homeItems.setImages(String.valueOf(R.drawable.apple));
            items.add(homeItems);

        }
        HomeAdapter adapter = new HomeAdapter(context,items,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickRecycler(String name, int position) {
        Fragment fragment = new subCategoryFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_home, fragment);
        fragmentTransaction.addToBackStack(null);
        toolbar.setTitle(name);
        fragmentTransaction.commit();

       /* Fragment fragment = new subCategoryFragment();
        Bundle args = new Bundle();
        args.putString("data", "This data has sent to FragmentTwo");
        Log.d("data", "This data has sent to FragmentTwo");
        fragment.setArguments(args);
        FragmentTransaction transaction = HomeFragment.this.getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_home, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
*/
    }
}
