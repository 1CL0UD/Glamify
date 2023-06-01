package com.project.glamify;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    private Fragment homeFragment = new HomeFragment();
    private Fragment ordersFragment = new OrdersFragment();

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment activeFragment = homeFragment;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private MaterialToolbar topAppBar;

    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest, signUpRequest;

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode) {
//            case REQ_ONE_TAP:
//                try {
//                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
//                    String idToken = credential.getGoogleIdToken();
//                    String username = credential.getId();
//                    String password = credential.getPassword();
//                    if (idToken !=  null) {
//                        // Got an ID token from Google. Use it to authenticate
//                        // with your backend.
//                        Log.d(TAG, "Got ID token.");
//                    } else if (password != null) {
//                        // Got a saved username and password. Use them to authenticate
//                        // with your backend.
//                        Log.d(TAG, "Got password.");
//                    }
//                } catch (ApiException e) {
//                    // ...
//                }
//                break;
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        oneTapClient = Identity.getSignInClient(this);
//        signInRequest = BeginSignInRequest.builder()
//                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
//                        .setSupported(true)
//                        .build())
//                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                        .setSupported(true)
//                        // Your server's client ID, not your Android client ID.
//                        .setServerClientId(getString(R.string.default_web_client_id))
//                        // Only show accounts previously used to sign in.
//                        .setFilterByAuthorizedAccounts(true)
//                        .build())
//                // Automatically sign in when exactly one credential is retrieved.
//                .setAutoSelectEnabled(true)
//                .build();
//
//        oneTapClient.beginSignIn(signUpRequest)
//                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
//                    @Override
//                    public void onSuccess(BeginSignInResult result) {
//                        try {
//                            startIntentSenderForResult(
//                                    result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
//                                    null, 0, 0, 0);
//                        } catch (IntentSender.SendIntentException e) {
//                            Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage());
//                        }
//                    }
//                })
//                .addOnFailureListener(this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // No saved credentials found. Launch the One Tap sign-up flow, or
//                        // do nothing and continue presenting the signed-out UI.
//                        Log.d(TAG, e.getLocalizedMessage());
//                    }
//                });


        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.navHome);

//        fragmentManager.beginTransaction().add(R.id.nav_host_fragment, homeFragment, "3").commit();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigation, navController);

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navShop:
                        navController.navigate(R.id.shopFragment);
                        break;
                    case R.id.navForYou:
                        navController.navigate(R.id.forYouFragment);
                        break;
                    case R.id.navHome:
                        navController.navigate(R.id.homeFragment);
                        break;
                    case R.id.navOrders:
                        navController.navigate(R.id.ordersFragment);
                        break;
                    case R.id.navProfile:
                        navController.navigate(R.id.profileFragment);
                }
                return true;
            }
        });
    }
}