package com.makein.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.adapter.SubCategoriesAdapter;
import com.makein.client.models.MyResponse;

import java.util.List;

public class subCategoryFragment extends Fragment implements SubCategoriesAdapter.RecyclerViewClickListener {
    // Toolbar toolbar ;
    Context context;
    List<MyResponse.SubProds> items;
    RecyclerView recyclerView;
    String CategoryName;

    subCategoryFragment(List<MyResponse.SubProds> items, String CategoryName) {
        this.items = items;
        this.CategoryName = CategoryName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sub_category_fragment, container, false);

        context = getContext();
        getActivity().setTitle(CategoryName);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        recyclerView = v.findViewById(R.id.sub_cat_recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

       /* ArrayList<HomeItems> items = new ArrayList<>();
        HomeItems homeItems = new HomeItems();
        for (int i = 0; i <= 10; i++) {
            homeItems.setItemName("Category1");

            homeItems.setImages(String.valueOf(R.drawable.apple));
            items.add(homeItems);

        }*/
        SubCategoriesAdapter adapter = new SubCategoriesAdapter(context, items, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickRecycler(MyResponse.SubProds clickedItem, String name, int position) {
        Fragment fragment = new CategoryDetailsFragment(clickedItem, CategoryName, name);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_home, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
