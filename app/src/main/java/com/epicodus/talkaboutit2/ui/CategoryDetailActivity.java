package com.epicodus.talkaboutit2.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.talkaboutit2.Constants;
import com.epicodus.talkaboutit2.R;
import com.epicodus.talkaboutit2.adapters.FirebasePostListAdapter;
import com.epicodus.talkaboutit2.models.Category;
import com.epicodus.talkaboutit2.models.Post;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CategoryDetailActivity.class.getSimpleName();
    private Category mCategory;

    private Query mQuery;
    private Firebase mFireBasePostsRef;
    private FirebasePostListAdapter mAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Bind(R.id.newPostButton) Button mNewPostButton;
    @Bind(R.id.categoryNameTextView) TextView mCategoryNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);

        mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));
        mCategoryNameTextView.setText(mCategory.getName());

        mFireBasePostsRef = new Firebase(Constants.FIREBASE_URL_POSTS);
        setUpFirebaseQuery(mCategory);
        setUpRecyclerView();

        mNewPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mNewPostButton) {
            showNewPostDialog();
        }
    }

    private void setUpFirebaseQuery(Category category) {
        String posts = mFireBasePostsRef.toString();
        Firebase ref = new Firebase(posts);
        mQuery = ref.orderByChild("category").equalTo(category.getName());
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebasePostListAdapter(mQuery, Post.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showNewPostDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText title = (EditText) dialogView.findViewById(R.id.newPostTitleEditText);
        final EditText body = (EditText) dialogView.findViewById(R.id.newPostBodyEditText);

        dialogBuilder.setTitle("New Post");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String newTitle = title.getText().toString();
                String newBody = body.getText().toString();

                Post newPost = new Post(newTitle, newBody, mCategory.getName());
                Log.d(TAG, newPost.getTitle().toString());
                savePostToFirebase(newPost);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void savePostToFirebase(Post post) {
        Firebase addedPostRef = new Firebase(Constants.FIREBASE_URL_POSTS);
        addedPostRef.push().setValue(post);
    }

}
