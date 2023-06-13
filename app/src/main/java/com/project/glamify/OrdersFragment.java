package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class OrdersFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Orders");

        TabLayout tabLayout = view.findViewById(R.id.orderTabs);
        ViewPager2 viewPager = view.findViewById(R.id.orderViewPager);

// Set up the ViewPager2 with the adapter
        MyPagerAdapter adapter = new MyPagerAdapter(getParentFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Customize the tab text if needed
            tab.setText(adapter.getTabTitle(position));
        }).attach();

        return view;
    }
}