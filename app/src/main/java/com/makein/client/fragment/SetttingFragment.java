package com.makein.client.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.makein.client.R;

public class SetttingFragment extends Fragment {
    Toolbar toolbar ;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.setting_fragment, container, false);
        toolbar = v.findViewById(R.id.toolbar_setting);
        toolbar.setTitle("Settings");
        toolbar.setTitleTextColor(Color.WHITE);
        context = getContext();
        return v ;
    }
}
