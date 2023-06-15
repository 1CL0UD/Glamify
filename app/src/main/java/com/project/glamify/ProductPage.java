package com.project.glamify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ProductPage extends AppCompatActivity  {


    private Product product;
    private TextView prodTitle, prodPrice, prodDesc;
    private ImageView prodImg;

    private Button buyBtn;

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
                prodImg = findViewById(R.id.p_pic1);
                String imagePath = ((Product) product).getProduct_image();
                Picasso.get().load(imagePath).error(R.drawable.makeup1).into(prodImg);
            }else{

            }

            buyBtn = findViewById(R.id.buy_button);
            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ProductPage.this, PaymentActivity.class);
                    i.putExtra("product", product);
                    startActivity(i);
                }
            });
        }


    }



    
}