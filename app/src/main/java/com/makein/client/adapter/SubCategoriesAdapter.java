package com.makein.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.models.MyResponse;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.ViewHolder> {
    private List<MyResponse.SubProds> subItems;
    private Context context;
    private SubCategoriesAdapter.RecyclerViewClickListener mRecyclerListener;


    public SubCategoriesAdapter(Context context, List<MyResponse.SubProds> subItems, SubCategoriesAdapter.RecyclerViewClickListener mRecyclerListener) {
        this.subItems = subItems;
        this.context = context;
        this.mRecyclerListener = mRecyclerListener;
    }

    @Override
    public SubCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sub_cat_items, viewGroup, false);
        return new SubCategoriesAdapter.ViewHolder(view, mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(SubCategoriesAdapter.ViewHolder viewHolder, int i) {

        viewHolder.category.setText(subItems.get(i).name);
        viewHolder.category_rs.setText(subItems.get(i).description);
//        viewHolder.img.setImageResource(Integer.parseInt(subItems.get(i).img_urls.get(0)));
    }

    @Override
    public int getItemCount() {
        try {
            return subItems.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SubCategoriesAdapter.RecyclerViewClickListener mListener;
        private TextView category;
        private TextView category_rs;
        private ImageView img;

        public ViewHolder(View view, RecyclerViewClickListener mListener) {
            super(view);

            category = (TextView) view.findViewById(R.id.category_name);
            category_rs = (TextView) view.findViewById(R.id.category_rs);
            img = (ImageView) view.findViewById(R.id.image_sub);


            this.mListener = mListener;
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            MyResponse.SubProds clickedDataItem = subItems.get(pos);
            mListener.onClickRecycler(clickedDataItem,clickedDataItem.name, getAdapterPosition());
            Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.name, Toast.LENGTH_SHORT).show();

        }
    }

    public interface RecyclerViewClickListener {
        void onClickRecycler(MyResponse.SubProds Item, String name, int position);
    }


}
