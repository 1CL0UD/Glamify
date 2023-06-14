package com.project.glamify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditDetailActivity extends AppCompatActivity {

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private EditText aboutMe;
    private EditText phoneNumber;
    private EditText location;
    private Button updateData;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        aboutMe = findViewById(R.id.aboutme);
        phoneNumber = findViewById(R.id.phonenumber);
        location = findViewById(R.id.location);
        updateData = findViewById(R.id.updateButton);

        firestore = FirebaseFirestore.getInstance();

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(view);
            }
        });
    }

    public void updateData(View view) {
        String text1 = aboutMe.getText().toString();
        String text2 = phoneNumber.getText().toString();
        String text3 = location.getText().toString();

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestId().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String userId = "0";
        if(acct!=null){
            userId = acct.getId();
        }

        // Update the Firestore document with the new data
        DocumentReference documentRef = firestore.collection("user_detail").document(userId);
        documentRef.update("about", text1, "phone", text2, "location", text3)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditDetailActivity.this, "Update successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EditDetailActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}