package com.epicodus.talkaboutit2.ui;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.talkaboutit2.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = CategoryDetailActivity.class.getSimpleName();

    @Bind(R.id.newPostButton) Button mNewPostButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);

        mNewPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mNewPostButton) {
            showNewPostDialog();
        }
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
                Log.d(TAG, title.getText().toString());
                Log.d(TAG, body.getText().toString());
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

}
