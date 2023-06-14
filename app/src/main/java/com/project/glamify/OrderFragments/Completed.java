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
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.glamify.OrderFragments.AdapterClasses.OrderStatusAdapter;
import com.project.glamify.OrderFragments.ObjectClasses.OrderStatus;
import com.project.glamify.R;

import java.util.ArrayList;
import java.util.List;

public class Completed extends Fragment {
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private RecyclerView comRView;
    private List<OrderStatus> orderStatusList;

    private OrderStatusAdapter orderStatusAdapter;

    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        comRView = view.findViewById(R.id.com_rview);
        comRView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        orderStatusList = new ArrayList<>();

        orderStatusAdapter = new OrderStatusAdapter(orderStatusList);
        comRView.setAdapter(orderStatusAdapter);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(getContext(), gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        String userId = "0";
        if(acct!=null){
            userId = acct.getId();
        }

        db = FirebaseFirestore.getInstance();
        CollectionReference verifRef = db.collection("order_comp");
        Query query = verifRef.whereEqualTo("userId", userId);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
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
                        Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onResume() {
        super.onResume();

        // Run your Java file code here
        // This code will execute every time the fragment becomes visible

        // Example code: Print a message to the console
        Toast.makeText(getContext(), "Frag Opened", Toast.LENGTH_SHORT).show();
    }
}