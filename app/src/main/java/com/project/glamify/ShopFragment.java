package com.project.glamify;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        recyclerView = view.findViewById(R.id.recom_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        productList = new ArrayList<>();

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference productsRef = db.collection("recomm_product");

        productsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Product> productList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String title = documentSnapshot.getString("prod_title");
                            String price = documentSnapshot.getString("prod_price");
                            String desc = documentSnapshot.getString("prod_desc");
                            String img = documentSnapshot.getString("prod_img");
                            String img1 = documentSnapshot.getString("prod_img1");
                            String img2 = documentSnapshot.getString("prod_img2");

                            Product product = new Product(title, price, desc, img, img1, img2);
                            productList.add(product);
                        }

                        // Update the adapter with the retrieved product list
                        productAdapter.setProductList(productList);
                        productAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during data retrieval
                    }
                });


        // do something with homeText
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Shop");
//        toolbar.setTitleTextColor(Integer.parseInt("#191C1B"));

//        MaterialCardView cardView = view.findViewById(R.id.item1);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(requireContext(), "Card clicked", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), ProductPage.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void showRecomProducts() {

    }
}