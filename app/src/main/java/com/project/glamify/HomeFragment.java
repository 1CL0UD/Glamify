package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.glamify.Adapters.WoHomescreenAdapter;
import com.project.glamify.ObjectClasses.WeddingOrganizer;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView carouselHeader;
    private ArrayList<Contact> list;
    private RecyclerView wo_rv;
    private List<WeddingOrganizer> woList;
    private WoHomescreenAdapter woAdapter;

    private List<ShopCarousel> headerCarousel;

    private ShopCarouselAdapter headerCarouselAdapter;

    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Home Screen");

        db = FirebaseFirestore.getInstance();

        carouselHeader = view.findViewById(R.id.carousel_header);
        carouselHeader.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false));

        headerCarousel = new ArrayList<>();

        headerCarouselAdapter = new ShopCarouselAdapter(headerCarousel);
        carouselHeader.setAdapter(headerCarouselAdapter);

        CollectionReference carouselRef = db.collection("recomm_carousel");

        carouselRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<ShopCarousel> shopCarouselList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String img = documentSnapshot.getString("image");

                            ShopCarousel shopCarousel = new ShopCarousel(img);
                            shopCarouselList.add(shopCarousel);
                        }

                        // Update the adapter with the retrieved product list
                        headerCarouselAdapter.setShopCarouselList(shopCarouselList);
                        headerCarouselAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during data retrieval
                    }
                });

        wo_rv = view.findViewById(R.id.wo_homescreen);
        wo_rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        woList = new ArrayList<>();

        woAdapter = new WoHomescreenAdapter(woList);

        wo_rv.setAdapter(woAdapter);


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
