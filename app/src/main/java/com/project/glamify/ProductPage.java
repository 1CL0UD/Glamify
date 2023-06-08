package com.project.glamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductPage extends AppCompatActivity  {

    private Product product;
    private TextView prodTitle, prodPrice, prodDesc;

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


            // ... set other views with the product data
        }


    }

    
}