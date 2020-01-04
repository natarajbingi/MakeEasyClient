package com.makein.client.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.makein.client.R;
import com.makein.client.adapter.Pager;
import com.makein.client.controller.Controller;
import com.makein.client.controller.Sessions;
import com.makein.client.models.MyResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    Context context;
    Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = getApplicationContext();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("HOME");
        toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp);
        setSupportActionBar(toolbar);



        tabLayout = (TabLayout) findViewById(R.id.tabLayout_home);
        fragmentManager = getSupportFragmentManager();


        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.profile));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.settings));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_shopping_cart));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager = (ViewPager) findViewById(R.id.pager_home);
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(HomeActivity.this);


        // Set back button
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    toolbar.setNavigationIcon(R.drawable.arrow_back);
                } else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp);
                    toolbar.setTitle("HOME");
                    // toggle.syncState();
                }
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        int tabIconColor = ContextCompat.getColor(context, R.color.colorPrimaryDark);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int tabIconColor = ContextCompat.getColor(context, R.color.white);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_logout: {
                new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.logout_confirm)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onLogOut();
                            }

                        })
                        .setNegativeButton(getString(R.string.no), null)
                        .show();
                return true;
            }
            case android.R.id.home:
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                    toolbar.setNavigationIcon(R.drawable.arrow_back);
                } else {
                    toolbar.setNavigationIcon(R.drawable.ic_home_black_24dp);
                }

                return true;
            case R.id.action_shopping_cart:
//            fragmentClass = SubCategoryFragment.class;
//            SetFrag(fragmentClass);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void onLogOut() {
        Sessions.removeUserKey(context, Controller.Categories);
        Sessions.setUserString(HomeActivity.this, "FALSE", Controller.keepMeSignedStr);
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


}
