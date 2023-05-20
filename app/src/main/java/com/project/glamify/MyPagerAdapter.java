package com.project.glamify;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.glamify.OrderFragments.Completed;
import com.project.glamify.OrderFragments.OnGoing;
import com.project.glamify.OrderFragments.Processing;
import com.project.glamify.OrderFragments.Verifying;

public class MyPagerAdapter extends FragmentStateAdapter {
    private final String[] fragmentNames = {"Verifying", "Processing", "OnGoing", "Completed"};
    public MyPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return fragmentNames.length; // Replace with the number of tabs you want
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Replace with the appropriate Fragment for each position
        switch (position) {
            case 0:
                return new Verifying();
            case 1:
                return new Processing();
            case 2:
                return new OnGoing();
            case 3:
                return new Completed();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
    public String getTabTitle(int position) {
        return fragmentNames[position];
    }
}

