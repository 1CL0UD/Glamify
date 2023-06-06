package com.project.glamify;

import android.content.Intent;
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
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private GoogleSignInClient gsc;
    private RecyclerView recyclerView;
    private ArrayList<Profile> list;

    private Button signOutBtn;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.signoutprof);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click event here
                changeActivity();
            }
        });
    }

    private void changeActivity() {
        // Start the new activity here
        Intent intent = new Intent(getActivity(), ProfileBaseline.class);
        startActivity(intent);
    }


}