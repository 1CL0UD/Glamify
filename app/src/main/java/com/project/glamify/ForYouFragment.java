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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForYouFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForYouFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Contact> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_for_you, container, false);
//        TextView homeText = view.findViewById(R.id.for_you_text);
//        ImageView foryouprev = view.findViewById(R.id.for_you_preview);
        // do something with homeText
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("For You");

        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        list.addAll(ContactData.getListData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewContactAdapter cardViewContactAdapter= new CardViewContactAdapter(this);
        cardViewContactAdapter.setContactList(list);
        recyclerView.setAdapter(cardViewContactAdapter);

        return view;
    }
}