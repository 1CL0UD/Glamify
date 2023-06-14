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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.project.glamify.OrderFragments.ObjectClasses.OrderStatus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private ImageView userPicture;
    private TextView userName, userEmail;

    private TextView userdesc, userphone, userlocation;

    private FloatingActionButton btnSignOut;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userPicture = findViewById(R.id.userpicture);
        userName = findViewById(R.id.username);
        userEmail = findViewById(R.id.useremail);
        btnSignOut = findViewById(R.id.btnSignout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String userId = "0";
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            String ppUrl = acct.getPhotoUrl().toString();
            userName.setText(personName);
            userEmail.setText(personEmail);
            Picasso.get().load(ppUrl).into(userPicture);
            userId = acct.getId();
        }


        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        userphone = findViewById(R.id.userPhone);
        userlocation = findViewById(R.id.userLocation);
        userdesc = findViewById(R.id.userDesc);
        db = FirebaseFirestore.getInstance();
        db.collection("user_detail").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                String about = documentSnapshot.getString("about");
                String address = documentSnapshot.getString("location");
                String phone = documentSnapshot.getString("phone");
                userphone.setText(phone);
                userdesc.setText(about);
                userlocation.setText(address);

            }
        }).addOnFailureListener(e -> {
        //error
        });





    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(ProfileActivity.this,LoginActivity.class));
            }
        });
    }
}