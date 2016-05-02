package com.epicodus.talkaboutit2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.talkaboutit2.Constants;
import com.epicodus.talkaboutit2.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Firebase mAddedLocationRef;
    private ValueEventListener mAddedCategoryRefListener;
    @Bind(R.id.newCategory) EditText mNewCategory;
    @Bind(R.id.addCategoyButton)Button mAddCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAddedLocationRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_CATEGORY);

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
        Firebase addedLocationRef = new Firebase(Constants.FIREBASE_URL_SEARCHED_CATEGORY);
        addedLocationRef.push().setValue(category);
    }
}

