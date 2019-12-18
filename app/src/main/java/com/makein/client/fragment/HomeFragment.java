package com.makein.client.fragment;

import android.app.ProgressDialog;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.makein.client.R;
import com.makein.client.ServerHit.Api;
import com.makein.client.ServerHit.RetroCall;
import com.makein.client.adapter.HomeAdapter;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;
import com.makein.client.models.HomeItems;
import com.makein.client.models.MyResponse;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements HomeAdapter.RecyclerViewClickListener {
    Toolbar toolbar;
    Context context;
    LinearLayout home;
    private ProgressDialog dialog;
    MyResponse myResponse;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeMeHomeCategory;
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        toolbar = v.findViewById(R.id.toolbar_home);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);
        context = getContext();
        home = v.findViewById(R.id.home_layout_);
        recyclerView = v.findViewById(R.id.home_recycle);
        swipeMeHomeCategory = v.findViewById(R.id.swipeMeHomeCategory);

        dialog = new ProgressDialog(context);
        dialog.setMessage("in Progress, please wait.");

        myResponse = (MyResponse) Sessions.getUserObj(context, Controller.Categories, MyResponse.class);

        if (myResponse == null) {
            //  String userId = Sessions.getUserObject(context, Controller.userID);
            GetAllProdSubs("");
        } else {
            // RecyClPatch(myResponse);
            initViews();
        }

        swipeMeHomeCategory.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetAllProdSubs("");
            }
        });


        return v;
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);

          adapter = new HomeAdapter(context, myResponse.data, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickRecycler(MyResponse.Data data, int position) {
        if(data.subProds!=null) {
            Fragment fragment = new subCategoryFragment(data.subProds,data.name);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_home, fragment);
            fragmentTransaction.addToBackStack(null);
            toolbar.setTitle(data.name);
            fragmentTransaction.commit();
        }else{

            Controller.Toasty(context, "No Sub Category found.");
        }
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


    private void GetAllProdSubs(String created_by) {
        dialog.show();
        //creating request body for file
        RequestBody created_byB = RequestBody.create(MediaType.parse("text/plain"), created_by);
        //creating our api
        Api api = RetroCall.getClient();
        //creating a call and calling the upload image method
        Call<MyResponse> call = api.getallprodsubs(created_byB);
        //finally performing the call
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Controller.logPrint(call.request().toString(),null,response.body());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                assert response.body() != null;
                if (!response.body().error) {
                    Sessions.setUserObj(context, response.body(), Controller.Categories);
                    //  RecyClPatch(response.body());
                    myResponse = response.body();
                    initViews();
                    swipeMeHomeCategory.setRefreshing(false);
                } else {
                    Controller.Toasty(context, "Some error occurred...");
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Controller.Toasty(context, t.getMessage());
                Log.d("Err", t.getMessage());
            }
        });
    }

}
