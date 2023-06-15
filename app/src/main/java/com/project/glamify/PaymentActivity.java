package com.project.glamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {
    private Product product;
    private Button buyBtn;
    private GoogleSignInOptions gso;

    private GoogleSignInClient gsc;
    private FirebaseFirestore db;
    private DocumentReference sendRef;
    private String prodTitle, prodPrice, prodDesc;
    private String payment;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buyBtn = findViewById(R.id.buy_btn);

        toolbar = findViewById(R.id.topAppBar123);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            product = (Product) intent.getSerializableExtra("product");
            if(product != null){
                // Display the product details in your activity

                String prodTitle = (((Product) product).getProduct_title());

                String prodPrice = (((Product) product).getProduct_price());

                String prodDesc = (((Product) product).getProduct_desc());
            }else{

            }

            MaterialCardView card1 = findViewById(R.id.card1);
            MaterialCardView card2 = findViewById(R.id.card2);
            MaterialCardView card3 = findViewById(R.id.card3);
            card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    payment = "Credit Card";
                }
            });
            card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    payment = "OVO";
                }
            });
            card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    payment = "GoPay";
                }
            });
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveProductToFirestore(product, payment);
                }
            });
        }

    }

    private void saveProductToFirestore(Product product, String payment) {
        // Assuming you have already initialized Firestore in your app
        db = FirebaseFirestore.getInstance();

        // Create a new document in the "products" collection
        sendRef = db.collection("order_proc").document();

        // Set the data to be saved
        Map<String, Object> sendData = new HashMap<>();
        sendData.put("payment", payment);
        sendData.put("orderName", product.getProduct_title());
        sendData.put("image", product.getProduct_image());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            Toast.makeText(PaymentActivity.this, "Dapat ID", Toast.LENGTH_SHORT).show();
            String userId = acct.getId();
            sendData.put("userId", userId);
        }
        // Save the data to Firestore
        sendRef.set(sendData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data saved successfully
                        Intent i = new Intent(PaymentActivity.this, MainActivity.class);
                        startActivity(i);
//                        Toast.makeText(PaymentActivity.this, "Product saved to Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save data
                        Toast.makeText(PaymentActivity.this, "Failed to save product", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}