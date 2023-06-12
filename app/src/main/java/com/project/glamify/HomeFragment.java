package com.project.glamify;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.glamify.Adapters.CarouselHeaderAdapter;
import com.project.glamify.Adapters.WoHomescreenAdapter;
import com.project.glamify.ObjectClasses.WeddingOrganizer;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Contact> list;
    private ViewPager2 carouselHeader;

    private RecyclerView wo_rv;
    private List<WeddingOrganizer> woList;
    private WoHomescreenAdapter woAdapter;

    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home Screen");

//        carouselHeader = activity.findViewById(R.id.carouselHeader);
//
//        // Retrieve image links from Firestore and store in imageLinks list or array
//        List<String> imageLinks = new ArrayList<>();
//        imageLinks.add("https://example.com/image1.jpg");
//        imageLinks.add("https://example.com/image2.jpg");
//        // Add more image links as needed
//
//        CarouselHeaderAdapter carouselAdapter = new CarouselHeaderAdapter(imageLinks);
//        carouselHeader.setAdapter(carouselAdapter);

        wo_rv = view.findViewById(R.id.wo_homescreen);
        wo_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        woList = new ArrayList<>();

        woAdapter = new WoHomescreenAdapter(woList);

        wo_rv.setAdapter(woAdapter);

        db = FirebaseFirestore.getInstance();
        CollectionReference productsRef = db.collection("wo_home");

        productsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<WeddingOrganizer> woList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String title = documentSnapshot.getString("title");
                            String img = documentSnapshot.getString("img");
                            String article = documentSnapshot.getString("article");
                            String source = documentSnapshot.getString("source");

                            WeddingOrganizer wo = new WeddingOrganizer(title, img, article, source);
                            woList.add(wo);
                        }

                        // Update the adapter with the retrieved product list
                        woAdapter.setWoList(woList);
                        woAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during data retrieval
                    }
                });


        return view;
    }

}
