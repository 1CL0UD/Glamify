package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Profile> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        ImageView profprev = view.findViewById(R.id.profile_preview);

        CircleImageView profileImage = view.findViewById(R.id.profile_image);
        Glide.with(this).load(R.drawable.person).into(profileImage);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Profile");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_profile);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        list.addAll(ProfileMenu.getListData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CardViewProfileAdapter cardViewProfileAdapter= new CardViewProfileAdapter(getContext());
        cardViewProfileAdapter.setProfileList(list);
        recyclerView.setAdapter(cardViewProfileAdapter);

        return view;
    }


}