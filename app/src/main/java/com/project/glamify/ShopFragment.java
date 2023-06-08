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


public class ShopFragment extends Fragment implements OnItemClickListener{

    private RecyclerView recyclerView;
    private RecyclerView newProdrecyclerView;
    private RecyclerView carousel_recyclerView;
    private ProductAdapter productAdapter;
    private ProductAdapter newProductAdapter;

    private ShopCarouselAdapter shopCarouselAdapter;
    private List<Product> productList;

    private List<ShopCarousel> shopCarousel;

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

        carousel_recyclerView = view.findViewById(R.id.recom_carousel_recycler_view);
        carousel_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        shopCarousel = new ArrayList<>();

        shopCarouselAdapter = new ShopCarouselAdapter(shopCarousel);
        carousel_recyclerView.setAdapter(shopCarouselAdapter);

        CollectionReference carouselRef = db.collection("recomm_carousel");

        carouselRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<ShopCarousel> shopCarouselList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String img = documentSnapshot.getString("prod_img");

                            ShopCarousel shopCarousel = new ShopCarousel(img);
                            shopCarouselList.add(shopCarousel);
                        }

                        // Update the adapter with the retrieved product list
                        shopCarouselAdapter.setShopCarouselList(shopCarouselList);
                        shopCarouselAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during data retrieval
                    }
                });


        newProdrecyclerView = view.findViewById(R.id.new_recycler_view);
        newProdrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        productList = new ArrayList<>();

        newProductAdapter = new ProductAdapter(productList);
        newProdrecyclerView.setAdapter(newProductAdapter);

        CollectionReference newRef = db.collection("new_product");

        newRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Product> productList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String title = documentSnapshot.getString("title");
                            String price = documentSnapshot.getString("price");
                            String desc = documentSnapshot.getString("desc");
                            String img = documentSnapshot.getString("img");
                            String img1 = documentSnapshot.getString("img1");
                            String img2 = documentSnapshot.getString("img2");

                            Product product = new Product(title, price, desc, img, img1, img2);
                            productList.add(product);
                        }

                        // Update the adapter with the retrieved product list
                        newProductAdapter.setProductList(productList);
                        newProductAdapter.notifyDataSetChanged();
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


        return view;
    }

    private void showRecomProducts() {

    }

    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(getActivity(), ProductPage.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}