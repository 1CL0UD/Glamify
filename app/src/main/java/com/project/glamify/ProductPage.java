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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProductPage extends AppCompatActivity  {

    private GoogleSignInOptions gso;

    private GoogleSignInClient gsc;
    private Product product;
    private TextView prodTitle, prodPrice, prodDesc;

    private Button buyBtn;
    private FirebaseFirestore db;
    private DocumentReference sendRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        Intent intent = getIntent();
        if (intent != null) {
            product = (Product) intent.getSerializableExtra("product");

            if(product != null){
                // Display the product details in your activity
                prodTitle = findViewById(R.id.product_title123);
                prodTitle.setText(((Product) product).getProduct_title());
                prodPrice = findViewById(R.id.product_price);
                prodPrice.setText(((Product) product).getProduct_price());
                prodDesc = findViewById(R.id.product_desc);
                prodDesc.setText(((Product) product).getProduct_desc());
            }else{

            }

            buyBtn = findViewById(R.id.buy_button);
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Save data to Firestore
                    saveProductToFirestore(product);
                }
            });
        }


    }

    private void saveProductToFirestore(Product product) {
        // Assuming you have already initialized Firestore in your app
        db = FirebaseFirestore.getInstance();

        // Create a new document in the "products" collection
        sendRef = db.collection("order_proc").document();

        // Set the data to be saved
        Map<String, Object> sendData = new HashMap<>();
        sendData.put("orderName", product.getProduct_title());
        sendData.put("image", product.getProduct_image());
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            Toast.makeText(ProductPage.this, "Tidak Null", Toast.LENGTH_SHORT).show();
            String userId = acct.getId();
            sendData.put("userId", userId);
        }
        // Save the data to Firestore
        sendRef.set(sendData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Data saved successfully
//                        Toast.makeText(ProductPage.this, "Product saved to Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save data
                        Toast.makeText(ProductPage.this, "Failed to save product", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    
}