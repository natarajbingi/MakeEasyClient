package com.makein.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.fragment.CategoryDetailsFragment;
import com.makein.client.fragment.subCategoryFragment;
import com.makein.client.models.HomeItems;

import java.util.ArrayList;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {
    private ArrayList<HomeItems> subItems;
    private Context context;
    private SubCategoriesAdapter.RecyclerViewClickListener mRecyclerListener;


    public SubCategoriesAdapter(Context context, ArrayList<HomeItems> subItems , SubCategoriesAdapter.RecyclerViewClickListener mRecyclerListener ) {
        this.subItems = subItems;
        this.context = context;
        this.mRecyclerListener = mRecyclerListener;
    }

    @Override
    public SubCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_cat_items, viewGroup, false);
        return new SubCategoriesAdapter.ViewHolder(view,mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(SubCategoriesAdapter.ViewHolder viewHolder, int i) {

        viewHolder.category.setText(subItems.get(i).getItemName());
        viewHolder.img.setImageResource(Integer.parseInt(subItems.get(i).getImages()));
    }

    @Override
    public int getItemCount() {
        return subItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private SubCategoriesAdapter.RecyclerViewClickListener mListener;
        private TextView category;
        private ImageView img;
        public ViewHolder(View view, RecyclerViewClickListener mListener) {
            super(view);

            category = (TextView)view.findViewById(R.id.category_name);
            img = (ImageView) view.findViewById(R.id.image_sub);


            this.mListener = mListener;
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();


            HomeItems clickedDataItem = subItems.get(pos);

            mListener.onClickRecycler(clickedDataItem.getItemName(),getAdapterPosition());

            Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.getItemName(), Toast.LENGTH_SHORT).show();

        }
    }
    public interface RecyclerViewClickListener {

        void onClickRecycler(String name,int position);
    }


}
