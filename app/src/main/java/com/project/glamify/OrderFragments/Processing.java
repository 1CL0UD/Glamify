package com.project.glamify.OrderFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.glamify.OrderFragments.AdapterClasses.OrderStatusAdapter;
import com.project.glamify.OrderFragments.ObjectClasses.OrderStatus;
import com.project.glamify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Processing#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Processing extends Fragment {
    private RecyclerView procRView;
    private List<OrderStatus> orderStatusList;

    private OrderStatusAdapter orderStatusAdapter;

    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processing, container, false);

        procRView = view.findViewById(R.id.proc_rview);
        procRView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        orderStatusList = new ArrayList<>();

        orderStatusAdapter = new OrderStatusAdapter(orderStatusList);
        procRView.setAdapter(orderStatusAdapter);

        db = FirebaseFirestore.getInstance();
        CollectionReference procRef = db.collection("order_proc");

        procRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<OrderStatus> orderStatusList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String title = documentSnapshot.getString("orderName");
                            String img = documentSnapshot.getString("image");

                            OrderStatus orderStatus = new OrderStatus(title, img);
                            orderStatusList.add(orderStatus);
                        }

                        // Update the adapter with the retrieved product list
                        orderStatusAdapter.setOrderStatusList(orderStatusList);
                        orderStatusAdapter.notifyDataSetChanged();
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