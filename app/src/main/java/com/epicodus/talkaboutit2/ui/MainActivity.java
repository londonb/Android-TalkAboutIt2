package com.epicodus.talkaboutit2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.talkaboutit2.Constants;
import com.epicodus.talkaboutit2.R;
import com.epicodus.talkaboutit2.adapters.FirebaseCategoryListAdapter;
import com.epicodus.talkaboutit2.models.Category;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Query mQuery;
    private Firebase mFirebaseCategoriesRef;
    private FirebaseCategoryListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private Firebase mAddedLocationRef;
    private ValueEventListener mAddedCategoryRefListener;
    @Bind(R.id.newCategory) EditText mNewCategory;
    @Bind(R.id.addCategoyButton)Button mAddCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFirebaseCategoriesRef = new Firebase(Constants.FIREBASE_URL_CATEGORIES);
        mAddedLocationRef = new Firebase(Constants.FIREBASE_URL_CATEGORIES);

        setUpFirebaseQuery();
        setUpRecyclerView();

        mAddCategoryButton.setOnClickListener(this);



        mAddedCategoryRefListener =  mAddedLocationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String categories = dataSnapshot.getValue().toString();
                Log.d("Location updated", categories);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void setUpFirebaseQuery() {
        String category = mFirebaseCategoriesRef.toString();
        mQuery = new Firebase(category);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebaseCategoryListAdapter(mQuery, Category.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAddedLocationRef.removeEventListener(mAddedCategoryRefListener);
    }


    @Override
    public void onClick(View v) {
        if (v == mAddCategoryButton) {
            String category = mNewCategory.getText().toString();
            saveCategoryToFirebase(category);
        }
    }

    public void saveCategoryToFirebase(String category) {
        Firebase addedLocationRef = new Firebase(Constants.FIREBASE_URL_CATEGORIES);
        addedLocationRef.push().setValue(category);
        mNewCategory.setText("");
    }
}

