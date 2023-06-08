package com.project.glamify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ForYouFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ForYouArticles> articleList;

    private ForYouArticleAdapter forYouArticleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_for_you, container, false);



        recyclerView = view.findViewById(R.id.articles_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        articleList = new ArrayList<>();

        forYouArticleAdapter = new ForYouArticleAdapter(articleList);
        recyclerView.setAdapter(forYouArticleAdapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference articlesRef = db.collection("foryou_articles");

        articlesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<ForYouArticles> articlesList = new ArrayList<>();

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String title = documentSnapshot.getString("title");
                            String article = documentSnapshot.getString("article");
                            String source = documentSnapshot.getString("source");
                            String img = documentSnapshot.getString("image");

                            ForYouArticles articles = new ForYouArticles(img, title, article, source);
                            articlesList.add(articles);
                        }

                        // Update the adapter with the retrieved product list
                        forYouArticleAdapter.setForYouArticlesList(articlesList);
                        forYouArticleAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during data retrieval
                    }
                });



        AppCompatActivity activity = (AppCompatActivity) getActivity();
        MaterialToolbar toolbar = activity.findViewById(R.id.topAppBar);
        toolbar.setTitle("For You");

        return view;
    }
}