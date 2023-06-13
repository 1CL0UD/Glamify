package com.project.glamify;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private ImageView userPicture;
    private TextView userName, userEmail, editDetail;

    private TextView userdesc, userphone, userlocation;

    private FloatingActionButton btnSignOut;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//        ImageView profprev = view.findViewById(R.id.profile_preview);


        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("Profile");

        userPicture = view.findViewById(R.id.userpicture);
        userName = view.findViewById(R.id.username);
        userEmail = view.findViewById(R.id.useremail);
        btnSignOut = view.findViewById(R.id.btnSignout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(getContext(),gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
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

        userphone = view.findViewById(R.id.userPhone);
        userlocation = view.findViewById(R.id.userLocation);
        userdesc = view.findViewById(R.id.userDesc);
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

        editDetail = view.findViewById(R.id.editDetail); // Initialize the button

        editDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditDetailActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });
    }


}