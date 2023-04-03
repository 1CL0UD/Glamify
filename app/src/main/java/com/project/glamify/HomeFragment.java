package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;


public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView homeText = view.findViewById(R.id.home_text);
        // do something with homeText
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home");
        return view;
    }

    // other methods
}
