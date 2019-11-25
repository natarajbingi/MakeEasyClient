package com.makein.client.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.fragment.subCategoryFragment;
import com.makein.client.models.HomeItems;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<HomeItems> homeItems;
    private Context context;
    private   RecyclerViewClickListener mRecyclerListener;

    public HomeAdapter(Context context,ArrayList<HomeItems> homeItems,RecyclerViewClickListener mRecyclerListener ) {
        this.homeItems = homeItems;
        this.context = context;
        this.mRecyclerListener = mRecyclerListener;
}

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_items, viewGroup, false);
        return new ViewHolder(view,mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(homeItems.get(i).getItemName());
       viewHolder.img_android.setImageResource(Integer.parseInt(homeItems.get(i).getImages()));

    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private RecyclerViewClickListener mListener;
        private TextView tv_android;
        private ImageView img_android;
        private LinearLayout layout;
        public ViewHolder(View view, RecyclerViewClickListener mListener) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.item_name);
            img_android = (ImageView) view.findViewById(R.id.images);
            layout = view.findViewById(R.id.home_layout);

            this.mListener = mListener;
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int pos = getAdapterPosition();


            HomeItems clickedDataItem = homeItems.get(pos);

            mListener.onClickRecycler(clickedDataItem.getItemName(),getAdapterPosition());

            Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.getItemName(), Toast.LENGTH_SHORT).show();

        }
    }
    public interface RecyclerViewClickListener {

        void onClickRecycler(String name,int position);
    }

}
