package com.epicodus.talkaboutit2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.talkaboutit2.R;
import com.epicodus.talkaboutit2.models.Post;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/3/16.
 */
public class PostViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = PostViewHolder.class.getSimpleName();

    @Bind(R.id.postNameTextView) TextView mPostNameTextView;

    private Context mContext;
    private ArrayList<Post> mPosts = new ArrayList<>();

    public PostViewHolder(final View itemView, final ArrayList<Post> posts) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mPosts = posts;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
            }
        });
    }

    public void bindPost(Post post) {
        mPostNameTextView.setText(post.getTitle());
    }
}
