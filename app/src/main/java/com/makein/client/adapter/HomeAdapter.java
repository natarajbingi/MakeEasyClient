package com.makein.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.makein.client.R;
import com.makein.client.controller.BitmapTransform;
import com.makein.client.controller.Controller;
import com.makein.client.models.MyResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<MyResponse.Data> homeItems;
    private Context context;
    private RecyclerViewClickListener mRecyclerListener;

    public HomeAdapter(Context context, List<MyResponse.Data> homeItems, RecyclerViewClickListener mRecyclerListener) {
        this.homeItems = homeItems;
        this.context = context;
        this.mRecyclerListener = mRecyclerListener;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_items, viewGroup, false);
        return new ViewHolder(view, mRecyclerListener);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(homeItems.get(i).name);
        //viewHolder.img_android.setImageResource(Integer.parseInt();
        try {
            int size = (int) Math.ceil(Math.sqrt(800 * 600));
            // Loads given image
            Picasso.get()
                    .load(homeItems.get(i).img_url)
                    .transform(new BitmapTransform(800, 600))
                    .resize(size, size)
                    .centerInside()
                    // .noPlaceholder()
                    .placeholder(R.drawable.loader)
                    .error(R.drawable.load_failed)
                    .into(viewHolder.img_android);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return homeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewClickListener mListener;
        private TextView tv_android;
        private ImageView img_android;
        private LinearLayout layout;

        public ViewHolder(View view, RecyclerViewClickListener mListener) {
            super(view);

            tv_android = (TextView) view.findViewById(R.id.item_name);
            img_android = (ImageView) view.findViewById(R.id.images);
            layout = view.findViewById(R.id.home_layout);

            this.mListener = mListener;
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            try {
                mListener.onClickRecycler(homeItems.get(pos), pos);
            } catch (Exception e) {
                e.printStackTrace();
                Controller.Toasty(context, "No Sub Category found.");
            }
            //Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.name, Toast.LENGTH_SHORT).show();

        }
    }

    public interface RecyclerViewClickListener {
        void onClickRecycler(MyResponse.Data name, int position);
    }

}
