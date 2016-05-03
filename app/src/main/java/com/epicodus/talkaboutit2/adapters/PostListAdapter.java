package com.epicodus.talkaboutit2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.talkaboutit2.R;
import com.epicodus.talkaboutit2.models.Category;
import com.epicodus.talkaboutit2.models.Post;

import java.util.ArrayList;

/**
 * Created by Guest on 5/3/16.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private ArrayList<Post> mPosts = new ArrayList<>();
    private Context mContext;

    public PostListAdapter(Context context, ArrayList<Post> posts) {
        mContext = context;
        mPosts = posts;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        PostViewHolder viewHolder = new PostViewHolder(view, mPosts);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bindPost(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
