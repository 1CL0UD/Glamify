package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;


public class ShopFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
//        TextView homeText = view.findViewById(R.id.shop_text);
        ImageView shopprev = view.findViewById(R.id.shop_preview);
        // do something with homeText
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Shop");
//        toolbar.setTitleTextColor(Integer.parseInt("#191C1B"));
        return view;
    }
}